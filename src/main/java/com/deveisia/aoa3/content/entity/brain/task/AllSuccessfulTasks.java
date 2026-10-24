package com.deveisia.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.GateBehavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class AllSuccessfulTasks<E extends LivingEntity> extends GateBehavior<E>  {
	public AllSuccessfulTasks(List<Pair<? extends BehaviorControl<? super E>, Integer>> taskList) {
		this(ImmutableMap.of(), taskList);
	}

	public AllSuccessfulTasks(Map<MemoryModuleType<?>, MemoryStatus> memoryRequirements, List<Pair<? extends BehaviorControl<? super E>, Integer>> taskList) {
		super(memoryRequirements, ImmutableSet.of(), GateBehavior.OrderPolicy.ORDERED, GateBehavior.RunningPolicy.TRY_ALL, taskList);
	}
}
