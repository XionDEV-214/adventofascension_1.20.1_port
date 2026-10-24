package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HealingFood extends Item {
	private final float healingAmount;

	public HealingFood(float healAmount, Item.Properties itemProperties) {
		super(itemProperties);

		this.healingAmount = healAmount;
	}

	public float getHealAmount() {
		return this.healingAmount;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		float percentHunger = 100;

		if (entity instanceof Player)
			percentHunger = Math.min((20 - ((Player)entity).getFoodData().getFoodLevel()) / (float)this.getFoodProperties().getNutrition(), 1);

		ItemStack returnStack = super.finishUsingItem(stack, world, entity);

		if (entity instanceof Player)
			EntityUtil.healEntity(entity, healingAmount * percentHunger);

		return returnStack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.healingFood.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.healingFood.desc.2", LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Float.toString(healingAmount))));
	}
}
