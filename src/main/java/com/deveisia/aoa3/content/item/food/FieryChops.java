package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FieryChops extends Item {
	public FieryChops() {
		super(AoAItemGroups.FOOD.properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5f).build()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
