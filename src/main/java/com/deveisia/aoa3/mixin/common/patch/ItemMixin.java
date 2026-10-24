package com.deveisia.aoa3.mixin.common.patch;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(method = "<init>", at = @At("TAIL"))
	private void aoa3$captureItemGroup(Item.Properties properties, CallbackInfo callback) {
		AoAItemGroups.captureItem((Item)(Object)this, properties);
	}
}
