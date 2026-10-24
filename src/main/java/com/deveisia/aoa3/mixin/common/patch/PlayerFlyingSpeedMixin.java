package com.deveisia.aoa3.mixin.common.patch;

import com.deveisia.aoa3.event.dimension.LunalusEvents;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerFlyingSpeedMixin {
	@Inject(method = "getFlyingSpeed", at = @At("RETURN"), cancellable = true)
	private void aoa3$lunalusAirControl(CallbackInfoReturnable<Float> callback) {
		if (LunalusEvents.hasEnhancedAirControl((Player)(Object)this))
			callback.setReturnValue(Math.max(callback.getReturnValueF(), 0.05f));
	}
}
