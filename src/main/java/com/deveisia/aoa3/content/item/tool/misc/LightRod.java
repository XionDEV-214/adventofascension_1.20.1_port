package com.deveisia.aoa3.content.item.tool.misc;

import com.deveisia.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LightRod extends HaulingRod {
	public LightRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	protected HaulingFishingBobberEntity getNewBobber(Player player, ItemStack stack, int lureMod, int luckMod) {
		return new HaulingFishingBobberEntity(player, player.level(), stack, luckMod, lureMod);
	}

	@Override
	public float getHaulStrengthMod(Player player, ItemStack stack, HaulingFishingBobberEntity bobber) {
		return super.getHaulStrengthMod(player, stack, bobber) * 2f;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
