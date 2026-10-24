package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.ItemUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class OldBoot extends ArmorItem {
	public OldBoot() {
		super(ItemUtil.customArmourMaterial("aoa3:old_boot", 30, new int[] {2, 2, 2, 2}, 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0), ArmorItem.Type.BOOTS, AoAItemGroups.MISC_ITEMS.properties().rarity(Rarity.RARE));
	}
}
