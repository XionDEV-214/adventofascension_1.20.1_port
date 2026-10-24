package com.deveisia.aoa3.mixin.common.function;

import com.deveisia.aoa3.event.custom.AoAEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public class WorkbenchContainerMixin {
	@Inject(method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V", shift = At.Shift.AFTER))
	private static void aoa3$recipeEventHook(AbstractContainerMenu menu, Level world, Player player, CraftingContainer inventory, ResultContainer resultInventory, CallbackInfo callback) {
		if (!resultInventory.getItem(0).isEmpty() && AoAEvents.firePlayerCraftingEvent(player, resultInventory.getItem(0), inventory, resultInventory))
			resultInventory.setItem(0, ItemStack.EMPTY);
	}
}
