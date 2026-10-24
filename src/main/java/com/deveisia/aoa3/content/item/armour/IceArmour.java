package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IceArmour extends AdventArmour {
	public IceArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:ice", 12, new int[] {2, 6, 6, 2}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ICE;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slotId, boolean isSelected) {
		if (!(entity instanceof Player player) || player.getItemBySlot(getEquipmentSlot()) != stack)
			return;

		if (!world.isClientSide && stack.getDamageValue() > 0 && player.getRandom().nextFloat() < 0.02f && WorldUtil.getAmbientTemperature(world, player.blockPosition()) < 0.15f)
			stack.setDamageValue(stack.getDamageValue() - 1);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ice_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
