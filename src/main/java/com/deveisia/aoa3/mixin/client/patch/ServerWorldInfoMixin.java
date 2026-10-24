package com.deveisia.aoa3.mixin.client.patch;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class ServerWorldInfoMixin {
	@Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
	private void aoa3$worldGenSettingsLifecycle(CallbackInfoReturnable<Lifecycle> callback) {
		callback.setReturnValue(Lifecycle.stable());
	}
}
