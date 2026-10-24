package com.deveisia.aoa3.integration.jei.recipe.infusion;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.recipe.InfusionRecipe;
import com.deveisia.aoa3.integration.jei.recipe.AoARecipeCategory;
import com.deveisia.aoa3.util.LocaleUtil;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class InfusionRecipeCategory extends AoARecipeCategory<InfusionRecipe> {
	public static final RecipeType<InfusionRecipe> TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "infusion", InfusionRecipe.class);
	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/containers/infusion_table.png");

	public InfusionRecipeCategory(IGuiHelper guiHelper) {
		this(guiHelper, TYPE, "recipe.aoa3.infusion");
	}

	protected InfusionRecipeCategory(IGuiHelper guiHelper, RecipeType<InfusionRecipe> type, String titleKey) {
		super(guiHelper, type, titleKey, TEXTURE, 10, 10, 156, 66, new ItemStack(AoABlocks.INFUSION_TABLE.get()));
	}

	protected ItemStack getOutput(InfusionRecipe recipe) {
		return recipe.getResultItem(null);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, InfusionRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.OUTPUT, 128, 25).addItemStack(getOutput(recipe));

		if (!recipe.getRecipeInput().isEmpty())
			builder.addSlot(RecipeIngredientRole.INPUT, 7, 25).addItemStack(recipe.getRecipeInput());

		for (int i = 0; i < recipe.getIngredients().size() && i < 9; i++) {
			builder.addSlot(RecipeIngredientRole.INPUT, 35 + (i % 3) * 18, 7 + (i / 3) * 18).addIngredients(recipe.getIngredients().get(i));
		}

		builder.setShapeless();
	}

	@Override
	public void draw(InfusionRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

		if (recipe.getMaxXp() <= 0)
			return;

		Minecraft mc = Minecraft.getInstance();
		String message = LocaleUtil.getLocaleString("gui.misc.skills.xp", String.valueOf((recipe.getMinXp() == recipe.getMaxXp() ? recipe.getMaxXp() : recipe.getMinXp() + "-" + recipe.getMaxXp())));

		guiGraphics.drawString(mc.font, message, 150 - mc.font.width(message), 50, 0xFF8F8F8F, true);
	}
}
