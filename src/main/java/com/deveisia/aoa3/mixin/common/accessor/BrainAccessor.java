package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Optional;

@Mixin(Brain.class)
public interface BrainAccessor {
	@Accessor("memories")
	Map<MemoryModuleType<?>, Optional<? extends ExpirableValue<?>>> getMemories();

	@Accessor("sensors")
	Map<SensorType<?>, Sensor<?>> getSensors();
}
