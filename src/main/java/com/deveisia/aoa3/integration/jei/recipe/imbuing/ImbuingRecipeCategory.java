package com.deveisia.aoa3.integration.jei.recipe.imbuing;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.recipe.InfusionRecipe;
import com.deveisia.aoa3.integration.jei.recipe.infusion.InfusionRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;

public class ImbuingRecipeCategory extends InfusionRecipeCategory {
	public static final RecipeType<InfusionRecipe> TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "imbuing", InfusionRecipe.class);

	public ImbuingRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper, TYPE, "recipe.aoa3.imbuing");
	}

	@Override
	protected ItemStack getOutput(InfusionRecipe recipe) {
		return recipe.getEnchantmentAsBook();
	}
}
