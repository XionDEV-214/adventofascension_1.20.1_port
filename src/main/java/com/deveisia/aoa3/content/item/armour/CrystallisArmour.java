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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class CrystallisArmour extends AdventArmour {
	public CrystallisArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:crystallis", 56, new int[] {5, 6, 10, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.CRYSTALLIS;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (event.getSource().getEntity() instanceof LivingEntity) {
			if (slots == null && DamageUtil.isMeleeDamage(event.getSource()) || DamageUtil.isRangedDamage(event.getSource(), plData.player(), event.getAmount())) {
				event.getSource().getEntity().hurt(plData.player().damageSources().thorns(plData.player()), event.getAmount());
				plData.player().getInventory().hurtArmor(plData.player().damageSources().generic(), event.getAmount() * 2, Inventory.ALL_ARMOR_SLOTS);
			}
			else if (slots != null && plData.equipment().getCurrentFullArmourSet() != setType() && DamageUtil.isMeleeDamage(event.getSource())) {
				event.getSource().getEntity().hurt(plData.player().damageSources().thorns(plData.player()), event.getAmount() * slots.size() / 4f);
				plData.player().getInventory().hurtArmor(plData.player().damageSources().generic(), event.getAmount() * 2, Inventory.ALL_ARMOR_SLOTS);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.crystallis_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.crystallis_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.crystallis_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
