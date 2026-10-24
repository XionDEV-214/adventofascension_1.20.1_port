package com.deveisia.aoa3.integration.jei.recipe.framebench;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.recipe.FrameBenchRecipe;
import com.deveisia.aoa3.integration.jei.recipe.AoARecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FrameBenchRecipeCategory extends AoARecipeCategory<FrameBenchRecipe> {
	public static final RecipeType<FrameBenchRecipe> TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "frame_bench", FrameBenchRecipe.class);
	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/containers/frame_bench.png");

	public FrameBenchRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper, TYPE, "recipe.aoa3.frameBench", TEXTURE, 10, 12, 156, 60, new ItemStack(AoABlocks.FRAME_BENCH.get()));
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, FrameBenchRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 22).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 139, 22).addItemStack(recipe.getResultItem(null));
	}

	@Override
	public void draw(FrameBenchRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

		drawButton(guiGraphics, AoAItems.CROSSBOW_FRAME.get(), recipe, 45, 1);
		drawButton(guiGraphics, AoAItems.BLASTER_FRAME.get(), recipe, 65, 1);
		drawButton(guiGraphics, AoAItems.CANNON_FRAME.get(), recipe, 85, 1);
		drawButton(guiGraphics, AoAItems.HELMET_FRAME.get(), recipe, 35, 21);
		drawButton(guiGraphics, AoAItems.CHESTPLATE_FRAME.get(), recipe, 55, 21);
		drawButton(guiGraphics, AoAItems.LEGGINGS_FRAME.get(), recipe, 75, 21);
		drawButton(guiGraphics, AoAItems.BOOTS_FRAME.get(), recipe, 95, 21);
		drawButton(guiGraphics, AoAItems.GUN_FRAME.get(), recipe, 45, 41);
		drawButton(guiGraphics, AoAItems.SHOTGUN_FRAME.get(), recipe, 65, 41);
		drawButton(guiGraphics, AoAItems.SNIPER_FRAME.get(), recipe, 85, 41);
	}

	private void drawButton(GuiGraphics guiGraphics, Item frame, FrameBenchRecipe recipe, int x, int y) {
		guiGraphics.blit(TEXTURE, x, y, 176, recipe.getResultItem(null).getItem() == frame ? 21 : 39, 18, 18);
		guiGraphics.renderFakeItem(new ItemStack(frame), x + 1, y + 1);
	}
}
