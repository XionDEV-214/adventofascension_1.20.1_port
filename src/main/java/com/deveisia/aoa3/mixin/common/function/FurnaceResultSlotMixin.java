package com.deveisia.aoa3.mixin.common.function;

import com.deveisia.aoa3.event.custom.AoAEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceResultSlot.class)
public class FurnaceResultSlotMixin {
	@Inject(method = "onTake", at = @At("HEAD"))
	public void aoa3$onTake(Player player, ItemStack stack, CallbackInfo callback) {
		AoAEvents.firePlayerSmeltingEvent(player, stack, ((Slot)(Object)this).container);
	}
}
