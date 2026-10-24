package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Tea extends Item {
	public Tea() {
		super(AoAItemGroups.FOOD.properties()
				.craftRemainder(AoAItems.CUP.get())
				.stacksTo(1)
				.food(new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.REGENERATION, 50).level(2).build(), 1)
						.effect(new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 130).build(), 1)
						.build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		ItemStack consumedStack = super.finishUsingItem(stack, world, user);

		return user instanceof Player && ((Player)user).getAbilities().instabuild ? consumedStack : getCraftingRemainingItem(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
