package com.deveisia.aoa3.mixin.common.function;

import com.deveisia.aoa3.event.custom.AoAEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceMenu.class)
public class AbstractFurnaceContainerMixin {
	@Inject(method = "quickMoveStack", at = @At("HEAD"))
	public void aoa3$quickMoveStack(Player player, int slotIndex, CallbackInfoReturnable<ItemStack> callback) {
		if (slotIndex != 2)
			return;

		Slot mixinSlot = ((AbstractContainerMenu)(Object)this).slots.get(slotIndex);

		if (mixinSlot.hasItem())
			AoAEvents.firePlayerSmeltingEvent(player, mixinSlot.getItem(), mixinSlot.container);
	}
}
