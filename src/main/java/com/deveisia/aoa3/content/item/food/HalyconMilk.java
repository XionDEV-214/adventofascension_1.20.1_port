package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class HalyconMilk extends Item {
	public HalyconMilk() {
		super(AoAItemGroups.FOOD.properties().craftRemainder(Items.BUCKET).stacksTo(1)
				.craftRemainder(Items.BUCKET)
				.food(new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.CONFUSION, 100).build(), 1)
						.build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide) {
			EntityUtil.healEntity(entity, 2);
			entity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

			if (entity instanceof ServerPlayer) {
				ServerPlayer player = (ServerPlayer)entity;

				CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}

		if (entity instanceof Player && !((Player)entity).getAbilities().instabuild)
			stack.shrink(1);

		return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
