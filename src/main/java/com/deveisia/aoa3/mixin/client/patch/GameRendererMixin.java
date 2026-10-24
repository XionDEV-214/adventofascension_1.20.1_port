package com.deveisia.aoa3.mixin.client.patch;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@ModifyConstant(method = "pick(F)V",
			constant = @Constant(doubleValue = 9.0D),
			require = 0)
	private double aoa3$checkMaxRange(double defaultDist) {
		return 100;
	}
}
