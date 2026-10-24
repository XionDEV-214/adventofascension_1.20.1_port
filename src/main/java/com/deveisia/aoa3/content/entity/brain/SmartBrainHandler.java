package com.deveisia.aoa3.content.entity.brain;

import com.deveisia.aoa3.mixin.common.accessor.BehaviorAccessor;
import com.deveisia.aoa3.mixin.common.accessor.BrainAccessor;
import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.Level;

public class SmartBrainHandler<T extends LivingEntity & SmartBrainOwner<T>> {
	private static final HashMap<EntityType<? extends LivingEntity>, ImmutableList<MemoryModuleType<?>>> brainMemoryCache = new HashMap<>();

	private final T owner;
	private Brain<T> brain = null;

	private final boolean saveMemories;
	private final boolean hasDynamicTasks;

	public SmartBrainHandler(T owner) {
		this(owner, false);
	}

	public SmartBrainHandler(T owner, boolean hasDynamicTasks) {
		this(owner, false, hasDynamicTasks);
	}

	public SmartBrainHandler(T owner, boolean saveMemories, boolean hasDynamicTasks) {
		this.owner = owner;
		this.saveMemories = saveMemories;
		this.hasDynamicTasks = hasDynamicTasks;
	}

	public Brain.Provider<T> getBrainCodec() {
		if (!this.hasDynamicTasks && brainMemoryCache.containsKey(this.owner.getType()))
			return Brain.provider(brainMemoryCache.get(this.owner.getType()), this.owner.getSensors());

		if (this.brain != null)
			return Brain.provider(ImmutableList.copyOf(((BrainAccessor)this.brain).getMemories().keySet()), this.owner.getSensors());

		ImmutableList<SensorType<? extends Sensor<? super T>>> sensors = owner.getSensors();

		return Brain.provider(getMemoryList(compileTasks(), sensors), sensors);
	}

	public Brain<T> getBrain() {
		return this.brain;
	}

	public Brain<T> makeBrain(Dynamic<?> codecLoader) {
		if (this.brain != null && !this.saveMemories)
			return this.brain;

		ImmutableList<SensorType<? extends Sensor<? super T>>> sensors = owner.getSensors();
		Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> taskList = compileTasks();
		ImmutableList<MemoryModuleType<?>> memories;

		if (!hasDynamicTasks && brainMemoryCache.containsKey(owner.getType())) {
			memories = brainMemoryCache.get(owner.getType());
		}
		else {
			memories = getMemoryList(taskList, sensors);

			if (!this.hasDynamicTasks)
				brainMemoryCache.put((EntityType<? extends LivingEntity>)this.owner.getType(), memories);
		}

		if (!saveMemories) {
			Codec<Brain<T>> brainCodec = emptyBrainCodec();
			this.brain = new Brain<T>(memories, sensors, ImmutableList.of(), () -> brainCodec);
		}
		else {
			this.brain = Brain.provider(memories, sensors).makeBrain(codecLoader);
		}

		sanityCheckBrainState(taskList);
		applyTasks(taskList);

		return this.brain;
	}

	private Codec<Brain<T>> emptyBrainCodec() {
		MutableObject<Codec<Brain<T>>> brainCodec = new MutableObject<Codec<Brain<T>>>();

		brainCodec.setValue(Codec.unit(() -> new Brain<T>(ImmutableList.of(), ImmutableList.of(), ImmutableList.of(), brainCodec::getValue)));

		return brainCodec.getValue();
	}

	protected ImmutableList<MemoryModuleType<?>> getMemoryList(Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> taskList, ImmutableList<SensorType<? extends Sensor<? super T>>> sensors) {
		if (!hasDynamicTasks && brainMemoryCache.containsKey(owner.getType()))
			return brainMemoryCache.get(owner.getType());

		HashSet<MemoryModuleType<?>> memoryTypes = new HashSet<MemoryModuleType<?>>();

		memoryTypes.add(MemoryModuleType.LOOK_TARGET);
		memoryTypes.add(MemoryModuleType.WALK_TARGET);
		memoryTypes.add(MemoryModuleType.ATTACK_TARGET);
		memoryTypes.add(MemoryModuleType.ATTACK_COOLING_DOWN);
		memoryTypes.add(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
		memoryTypes.add(MemoryModuleType.PATH);
		memoryTypes.add(MemoryModuleType.HURT_BY_ENTITY);
		memoryTypes.add(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES);

		taskList.forEach((key, value) -> value.getMiddle().forEach(task -> {
			if (task instanceof Behavior<?>)
				memoryTypes.addAll(((BehaviorAccessor)task).getEntryCondition().keySet());
		}));

		return ImmutableList.copyOf(memoryTypes);
	}

	private Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> compileTasks() {
		Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> map = new HashMap<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>>();

		map.put(Activity.CORE, owner.getCoreTasks());
		map.put(Activity.IDLE, owner.getIdleTasks());
		map.put(Activity.FIGHT, owner.getFightTasks());
		map.putAll(owner.getAdditionalTasks());

		map.entrySet().removeIf(entry -> entry.getValue().getMiddle().isEmpty());

		return map;
	}

	private void applyTasks(Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> taskList) {
		for (Map.Entry<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> tasksEntry : taskList.entrySet()) {
			Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>> tasks = tasksEntry.getValue();

			if (tasks.getRight() != null) {
				this.brain.addActivityAndRemoveMemoryWhenStopped(tasksEntry.getKey(), tasks.getLeft(), tasks.getMiddle(), tasks.getRight());
			}
			else {
				this.brain.addActivity(tasksEntry.getKey(), tasks.getLeft(), tasks.getMiddle());
			}
		}

		this.brain.setCoreActivities(this.owner.getAlwaysRunningActivities());
		this.brain.setDefaultActivity(this.owner.getDefaultActivity());
		this.brain.useDefaultActivity();
		this.owner.handleAdditionalBrainSetup(this.brain);
	}

	private void sanityCheckBrainState(Map<Activity, Triple<Integer, ImmutableList<? extends BehaviorControl<? super T>>, MemoryModuleType<?>>> taskList) {
		if (!FMLLoader.isProduction()) {
			for (Sensor<?> sensor : ((BrainAccessor)this.brain).getSensors().values()) {
				for (MemoryModuleType<?> memoryType : sensor.requires()) {
					if (!((BrainAccessor)this.brain).getMemories().containsKey(memoryType))
						Logging.logMessage(Level.ERROR, "Required memory module not present in entity brain. " + RegistryUtil.getId(this.owner.getType()).toString());
				}
			}
		}
	}

	public void tick() {
		this.owner.level().getProfiler().push("SmartBrain");
		this.brain.tick((ServerLevel)this.owner.level(), this.owner);
		this.brain.setActiveActivityToFirstValid(this.owner.getActivityPriorities());
		this.owner.level().getProfiler().pop();

		if (this.owner instanceof Mob)
			((Mob)this.owner).setAggressive(((Mob)this.owner).getTarget() != null);
	}
}
