package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEffectInstance.class)
public interface MobEffectInstanceAccessor {
	@Accessor("duration")
	int getDurationRaw();

	@Mutable
	@Accessor("duration")
	void setDurationRaw(int duration);

	@Accessor("hiddenEffect")
	MobEffectInstance getHiddenEffectRaw();
}
