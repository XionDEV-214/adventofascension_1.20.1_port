package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class NethengeicArmour extends AdventArmour {
	public NethengeicArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:nethengeic", 41, new int[] {3, 7, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.NETHENGEIC;
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots == null && plData.player().isOnFire())
			event.setAmount(event.getAmount() * 1.5f);

		if (slots != null && event.getEntity().isOnFire())
			event.setAmount(event.getAmount() * (1 + (0.1f * slots.size())));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.nethengeic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.nethengeic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
