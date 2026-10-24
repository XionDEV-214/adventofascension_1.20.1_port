package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.RandomUtil;
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class SpacekingArmour extends AdventArmour {
	public SpacekingArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:spaceking", 62, new int[] {4, 8, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SPACEKING;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && !DamageUtil.isEnvironmentalDamage(event.getSource()) && !DamageUtil.isPoisonDamage(event.getSource(), plData.player(), event.getAmount())) {
			Player pl = plData.player();

			if (!pl.level().isClientSide && pl.getHealth() > 0 && RandomUtil.oneInNChance(3)) {

			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

	}
}
