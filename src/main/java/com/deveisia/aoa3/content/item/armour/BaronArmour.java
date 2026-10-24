package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BaronArmour extends AdventArmour {
	public BaronArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:baron", 150, new int[] {4, 6, 9, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.BARON;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.baron_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
