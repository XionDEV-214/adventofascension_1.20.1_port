package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.ItemUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HiveChunk extends Item {
	public HiveChunk() {
		super(AoAItemGroups.MISC_ITEMS.properties());
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.getType() == AoAEntities.Mobs.THARAFLY.get() && attacker instanceof Player) {
			if (stack.getCount() <= 1) {
				attacker.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAItems.HIVE_EGG.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop((Player)attacker, new ItemStack(AoAItems.HIVE_EGG.get()));
				stack.shrink(1);
			}

			return true;
		}

		return false;
	}
}
