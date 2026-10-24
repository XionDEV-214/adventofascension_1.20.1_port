package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.ItemUtil;
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

public class Lunarade extends Item {
	public Lunarade() {
		super(AoAItemGroups.FOOD.properties()
				.craftRemainder(AoAItems.LUNARADE_MUG.get())
				.food(new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.NIGHT_VISION, 40).level(2).build(), 1)
						.effect(new EffectBuilder(MobEffects.JUMP, 40).build(), 1)
						.build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (!world.isClientSide)
			user.removeEffect(MobEffects.BLINDNESS);

		ItemStack mug = getCraftingRemainingItem(stack);
		ItemStack consumedStack = super.finishUsingItem(stack, world, user);

		if (consumedStack.isEmpty()) {
			consumedStack = mug;
		}
		else if (user instanceof Player) {
			Player player = (Player)user;

			if (!player.getAbilities().instabuild)
				ItemUtil.givePlayerItemOrDrop((Player)user, mug);
		}

		return consumedStack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
