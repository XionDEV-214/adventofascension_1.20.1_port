package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoAItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FloatingStone extends Item {
	public FloatingStone() {
		super(AoAItemGroups.MISC_ITEMS.properties());
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (entity.getY() < -10) {
			entity.teleportTo(entity.getX(), 257, entity.getZ());
			entity.setItem(new ItemStack(AoAItems.HEAVY_BOULDER.get(), 1));

			return true;
		}

		return false;
	}
}
