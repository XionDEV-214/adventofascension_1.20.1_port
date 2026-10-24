package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class YetiFingernails extends Item {
	public YetiFingernails() {
		super(new Item.Properties().rarity(Rarity.RARE).food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
		if (entity instanceof ServerPlayer)
			PlayerUtil.notifyPlayer((ServerPlayer)entity, Component.translatable("message.feedback.yetiFingernails.eat"));

		return super.finishUsingItem(stack, worldIn, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
