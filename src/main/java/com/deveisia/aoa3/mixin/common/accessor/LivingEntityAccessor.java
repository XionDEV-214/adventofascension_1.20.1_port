package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
	@Invoker("getJumpPower")
	float invokeGetJumpPower();

	@Invoker("getCurrentSwingDuration")
	int invokeGetCurrentSwingDuration();
}
