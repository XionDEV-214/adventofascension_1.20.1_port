package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class EyeCandy extends Item {
	public EyeCandy() {
		super(AoAItemGroups.FOOD.properties().food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().fast().build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof ServerPlayer)
			PlayerUtil.addResourceToPlayer((ServerPlayer)entityLiving, AoAResources.SPIRIT.get(), 10);

		return super.finishUsingItem(stack, worldIn, entityLiving);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 24;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
