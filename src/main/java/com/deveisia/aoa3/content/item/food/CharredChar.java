package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import javax.annotation.Nullable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class CharredChar extends Item {
	public CharredChar() {
		super(AoAItemGroups.FOOD.properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build()));
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return 1200;
	}
}
