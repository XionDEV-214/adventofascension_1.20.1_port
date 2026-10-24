package com.deveisia.aoa3.integration.jei;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.container.DivineStationContainer;
import com.deveisia.aoa3.common.container.FrameBenchContainer;
import com.deveisia.aoa3.common.container.InfusionTableContainer;
import com.deveisia.aoa3.common.container.WhitewashingTableContainer;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAContainers;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoARecipes;
import com.deveisia.aoa3.content.recipe.FrameBenchRecipe;
import com.deveisia.aoa3.content.recipe.InfusionRecipe;
import com.deveisia.aoa3.content.recipe.ToolInteractionRecipe;
import com.deveisia.aoa3.content.recipe.UpgradeKitRecipe;
import com.deveisia.aoa3.content.recipe.WhitewashingRecipe;
import com.deveisia.aoa3.integration.IntegrationManager;
import com.deveisia.aoa3.integration.jei.ingredient.subtype.TrophySubtypeInterpreter;
import com.deveisia.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeCategory;
import com.deveisia.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeCategory;
import com.deveisia.aoa3.integration.jei.recipe.infusion.InfusionRecipeCategory;
import com.deveisia.aoa3.integration.jei.recipe.toolinteraction.ToolInteractionRecipeExtension;
import com.deveisia.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeCategory;
import com.deveisia.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "core");
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.getCraftingCategory().addCategoryExtension(ToolInteractionRecipe.class, ToolInteractionRecipeExtension::new);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCatalyst(new ItemStack(AoABlocks.DIVINE_STATION.get()), UpgradeKitRecipeCategory.TYPE);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.INFUSION_TABLE.get()), InfusionRecipeCategory.TYPE, ImbuingRecipeCategory.TYPE);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.FRAME_BENCH.get()), FrameBenchRecipeCategory.TYPE);
		registration.addRecipeCatalyst(new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()), WhitewashingRecipeCategory.TYPE);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeTransferHandler(DivineStationContainer.class, AoAContainers.DIVINE_STATION.get(), UpgradeKitRecipeCategory.TYPE, 1, 2, 3, 36);
		registration.addRecipeTransferHandler(InfusionTableContainer.class, AoAContainers.INFUSION_TABLE.get(), ImbuingRecipeCategory.TYPE, 1, 11, 12, 36);
		registration.addRecipeTransferHandler(InfusionTableContainer.class, AoAContainers.INFUSION_TABLE.get(), InfusionRecipeCategory.TYPE, 1, 11, 12, 36);
		registration.addRecipeTransferHandler(FrameBenchContainer.class, AoAContainers.FRAME_BENCH.get(), FrameBenchRecipeCategory.TYPE, 0, 1, 2, 36);
		registration.addRecipeTransferHandler(WhitewashingTableContainer.class, AoAContainers.WHITEWASHING_TABLE.get(), WhitewashingRecipeCategory.TYPE, 0, 2, 2, 36);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (Minecraft.getInstance().getConnection() == null || !IntegrationManager.isJEIActive())
			return;

		RecipeManager recipeManager = Minecraft.getInstance().getConnection().getRecipeManager();
		List<InfusionRecipe> imbuingRecipes = new ArrayList<InfusionRecipe>();
		List<InfusionRecipe> infusionRecipes = new ArrayList<InfusionRecipe>();

		for (InfusionRecipe recipe : recipeManager.getAllRecipesFor(AoARecipes.INFUSION.getA())) {
			if (recipe.isEnchanting()) {
				imbuingRecipes.add(recipe);
			}
			else {
				infusionRecipes.add(recipe);
			}
		}

		registration.addRecipes(UpgradeKitRecipeCategory.TYPE, new ArrayList<UpgradeKitRecipe>(recipeManager.getAllRecipesFor(AoARecipes.UPGRADE_KIT.getA())));
		registration.addRecipes(ImbuingRecipeCategory.TYPE, imbuingRecipes);
		registration.addRecipes(InfusionRecipeCategory.TYPE, infusionRecipes);
		registration.addRecipes(FrameBenchRecipeCategory.TYPE, compileFrameBenchRecipes());
		registration.addRecipes(WhitewashingRecipeCategory.TYPE, compileWhitewashingRecipes());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCategories(
				new UpgradeKitRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new ImbuingRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new InfusionRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new FrameBenchRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new WhitewashingRecipeCategory(registration.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.GOLD_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.ORNATE_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
	}

	private static List<WhitewashingRecipe> compileWhitewashingRecipes() {
		List<WhitewashingRecipe> recipes = new ArrayList<WhitewashingRecipe>(2);

		recipes.add(new WhitewashingRecipe(AdventOfAscension.id("whitewashing_white"), AoAItems.WHITEWASHING_SOLUTION.get(), AoABlocks.WHITEWASH_BRICKS.get()));
		recipes.add(new WhitewashingRecipe(AdventOfAscension.id("whitewashing_dark"), AoAItems.DARKLY_POWDER.get(), AoABlocks.DARKWASH_BRICKS.get()));

		return recipes;
	}

	private static List<FrameBenchRecipe> compileFrameBenchRecipes() {
		List<FrameBenchRecipe> recipes = new ArrayList<FrameBenchRecipe>(10);

		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_crossbow"), AoAItems.CROSSBOW_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_blaster"), AoAItems.BLASTER_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_cannon"), AoAItems.CANNON_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_helmet"), AoAItems.HELMET_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_chestplate"), AoAItems.CHESTPLATE_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_leggings"), AoAItems.LEGGINGS_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_boots"), AoAItems.BOOTS_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_gun"), AoAItems.GUN_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_shotgun"), AoAItems.SHOTGUN_FRAME.get()));
		recipes.add(new FrameBenchRecipe(AdventOfAscension.id("frame_bench_sniper"), AoAItems.SNIPER_FRAME.get()));

		return recipes;
	}
}
