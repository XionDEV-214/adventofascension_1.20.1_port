package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.DamageUtil;
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

public class ZargoniteArmour extends AdventArmour {
	public ZargoniteArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:zargonite", 64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ZARGONITE;
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && DamageUtil.isMagicDamage(event.getSource(), event.getEntity(), event.getAmount()))
			event.setAmount((float)(event.getAmount() * (1 + (0.1 * slots.size()))));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.zargonite_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
