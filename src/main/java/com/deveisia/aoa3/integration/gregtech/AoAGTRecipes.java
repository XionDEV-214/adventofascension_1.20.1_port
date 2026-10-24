package com.deveisia.aoa3.integration.gregtech;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FORGE_HAMMER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MACERATOR_RECIPES;

public final class AoAGTRecipes {
	public static void addRecipes(Consumer<FinishedRecipe> provider) {
		for (AoAOrePrefixes.StoneType stoneType : AoAOrePrefixes.getStoneTypes()) {
			MACERATOR_RECIPES.recipeBuilder(AdventOfAscension.id("macerate_" + stoneType.id()))
					.inputItems(stoneType.block().get().asItem())
					.outputItems(TagPrefix.dust, stoneType.material().get())
					.duration(150).EUt(2)
					.save(provider);

			FORGE_HAMMER_RECIPES.recipeBuilder(AdventOfAscension.id("hammer_" + stoneType.id()))
					.inputItems(stoneType.block().get().asItem())
					.outputItems(TagPrefix.dustSmall, stoneType.material().get(), 2)
					.duration(10).EUt(16)
					.save(provider);
		}

		aoaOre(provider, AoABlocks.RUNIUM_ORE, AoAItems.RUNIUM_CHUNK, 3);
		aoaOre(provider, AoABlocks.CHARGED_RUNIUM_ORE, AoAItems.CHARGED_RUNIUM_CHUNK, 3);
		aoaOre(provider, AoABlocks.BLUE_GEMSTONE_ORE, AoAItems.BLUE_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.GREEN_GEMSTONE_ORE, AoAItems.GREEN_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.PURPLE_GEMSTONE_ORE, AoAItems.PURPLE_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.RED_GEMSTONE_ORE, AoAItems.RED_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.WHITE_GEMSTONE_ORE, AoAItems.WHITE_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.YELLOW_GEMSTONE_ORE, AoAItems.YELLOW_GEMSTONES, 2);
		aoaOre(provider, AoABlocks.CHESTBONE_FRAGMENTS_ORE, AoAItems.CHESTBONE_FRAGMENT, 2);
		aoaOre(provider, AoABlocks.FOOTBONE_FRAGMENTS_ORE, AoAItems.FOOTBONE_FRAGMENT, 2);
		aoaOre(provider, AoABlocks.LEGBONE_FRAGMENTS_ORE, AoAItems.LEGBONE_FRAGMENT, 2);
		aoaOre(provider, AoABlocks.SKULLBONE_FRAGMENTS_ORE, AoAItems.SKULLBONE_FRAGMENT, 2);
	}

	private static void aoaOre(Consumer<FinishedRecipe> provider, RegistryObject<Block> ore, Supplier<Item> output, int amount) {
		String id = ore.getId().getPath();

		MACERATOR_RECIPES.recipeBuilder(AdventOfAscension.id("macerate_" + id))
				.inputItems(ore.get().asItem())
				.outputItems(output.get(), amount)
				.duration(300).EUt(2)
				.save(provider);

		FORGE_HAMMER_RECIPES.recipeBuilder(AdventOfAscension.id("hammer_" + id))
				.inputItems(ore.get().asItem())
				.outputItems(output.get(), Math.max(1, amount - 1))
				.duration(10).EUt(16)
				.save(provider);
	}
}
