package com.deveisia.aoa3.integration.jei.recipe.toolinteraction;

import com.deveisia.aoa3.content.recipe.ToolInteractionRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolInteractionRecipeExtension implements ICraftingCategoryExtension {
	private final ToolInteractionRecipe recipe;

	public ToolInteractionRecipeExtension(ToolInteractionRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();

		for (Ingredient ingredient : recipe.getIngredients()) {
			inputs.add(Arrays.asList(ingredient.getItems()));
		}

		craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
		craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, List.of(recipe.getResultItem(null)));
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}
}
