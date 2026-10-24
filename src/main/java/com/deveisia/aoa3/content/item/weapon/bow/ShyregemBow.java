package com.deveisia.aoa3.content.item.weapon.bow;

import com.deveisia.aoa3.common.registration.AoAItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ShyregemBow extends BaseBow {
	public ShyregemBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		Item repairItem = repairMaterial.getItem();

		return repairItem == AoAItems.SHYRESTONE_INGOT.get() || repairItem == AoAItems.SHYREGEM.get() || super.isValidRepairItem(stack, repairMaterial);
	}
}
