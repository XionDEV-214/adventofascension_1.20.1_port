package com.deveisia.aoa3.integration.jei.recipe;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public abstract class AoARecipeCategory<T> implements IRecipeCategory<T> {
	private final RecipeType<T> type;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	protected AoARecipeCategory(IGuiHelper guiHelper, RecipeType<T> type, String titleKey, ResourceLocation texture, int u, int v, int width, int height, ItemStack icon) {
		this.type = type;
		this.title = Component.translatable(titleKey);
		this.background = guiHelper.createDrawable(texture, u, v, width, height);
		this.icon = guiHelper.createDrawableItemStack(icon);
	}

	@Override
	public RecipeType<T> getRecipeType() {
		return this.type;
	}

	@Override
	public Component getTitle() {
		return this.title;
	}

	@Override
	public int getWidth() {
		return this.background.getWidth();
	}

	@Override
	public int getHeight() {
		return this.background.getHeight();
	}

	@Override
	public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}
}
