package com.deveisia.aoa3.mixin.client.patch;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	@Redirect(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"), require = 0)
	private static boolean aoa3$isCrossbowType(ItemStack stack, Item item) {
		if (item == Items.CROSSBOW)
			return stack.getItem() instanceof CrossbowItem;

		return stack.is(item);
	}
}
