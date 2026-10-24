package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EmbrodiumArmour extends AdventArmour {
	public EmbrodiumArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:embrodium", 45, new int[] {4, 7, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.EMBRODIUM;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null) {
			plData.getResource(AoAResources.SPIRIT.get()).addValue(0.08f);
		}
		else {
			Player pl = plData.player();
			float temp = WorldUtil.getAmbientTemperature(pl.level(), pl.blockPosition());

			if (temp > 0.8f)
				plData.getResource(AoAResources.SPIRIT.get()).addValue(0.08f * slots.size() * Math.min(1f, (temp / 2f)));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.embrodium_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.embrodium_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
