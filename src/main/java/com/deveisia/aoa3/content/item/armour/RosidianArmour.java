package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.ItemUtil;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class RosidianArmour extends AdventArmour {
	public RosidianArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:rosidian", 55, new int[] {4, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ROSIDIAN;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (!DamageUtil.isEnvironmentalDamage(event.getSource()) && !DamageUtil.isPoisonDamage(event.getSource(), plData.player(), event.getAmount())) {
			if (slots == null) {

			}
			else {

			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

	}
}
