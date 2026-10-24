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

public class HydrangicArmour extends AdventArmour {
	public HydrangicArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:hydrangic", 54, new int[] {4, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.HYDRANGIC;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.hydrangic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
