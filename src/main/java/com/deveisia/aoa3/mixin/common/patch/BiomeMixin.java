package com.deveisia.aoa3.mixin.common.patch;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {
	@Shadow
	public abstract boolean hasPrecipitation();

	@Inject(method = "shouldSnow", at = @At("HEAD"), cancellable = true)
	private void aoa3$shouldSnow(LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (!hasPrecipitation())
			callback.setReturnValue(false);
	}
}
