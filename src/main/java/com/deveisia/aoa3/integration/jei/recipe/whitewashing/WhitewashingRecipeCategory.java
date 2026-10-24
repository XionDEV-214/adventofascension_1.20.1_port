package com.deveisia.aoa3.integration.jei.recipe.whitewashing;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.recipe.WhitewashingRecipe;
import com.deveisia.aoa3.integration.jei.recipe.AoARecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class WhitewashingRecipeCategory extends AoARecipeCategory<WhitewashingRecipe> {
	public static final RecipeType<WhitewashingRecipe> TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "whitewashing", WhitewashingRecipe.class);

	public WhitewashingRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper, TYPE, "recipe.aoa3.whitewashing", ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/containers/basic_block.png"), 26, 22, 125, 18, new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()));
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, WhitewashingRecipe recipe, IFocusGroup focuses) {
		Item output = recipe.getResultItem(null).getItem();

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addItemStack(new ItemStack(Blocks.OBSIDIAN));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addItemStack(new ItemStack(output == AoABlocks.DARKWASH_BRICKS.get().asItem() ? AoAItems.DARKLY_POWDER.get() : AoAItems.WHITEWASHING_SOLUTION.get()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(new ItemStack(output, 2));
	}
}
