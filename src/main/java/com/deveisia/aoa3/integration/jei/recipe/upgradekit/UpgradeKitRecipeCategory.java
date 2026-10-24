package com.deveisia.aoa3.integration.jei.recipe.upgradekit;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.recipe.UpgradeKitRecipe;
import com.deveisia.aoa3.integration.jei.recipe.AoARecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class UpgradeKitRecipeCategory extends AoARecipeCategory<UpgradeKitRecipe> {
	public static final RecipeType<UpgradeKitRecipe> TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "upgrade_kit", UpgradeKitRecipe.class);

	public UpgradeKitRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper, TYPE, "recipe.aoa3.upgradeKit", ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/containers/basic_block.png"), 26, 22, 125, 18, new ItemStack(AoABlocks.DIVINE_STATION.get()));
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, UpgradeKitRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(recipe.getResultItem(null));
	}
}
