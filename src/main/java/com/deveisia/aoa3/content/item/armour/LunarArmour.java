package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LunarArmour extends AdventArmour {
	public LunarArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:lunar", 63, new int[] {4, 7, 10, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.LUNAR;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots != null)
			plData.player().addEffect(new MobEffectInstance(MobEffects.JUMP, -1, slots.size() - 1, true, false));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lunar_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lunar_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
