package com.deveisia.aoa3.integration.gregtech;

import net.minecraft.tags.BlockTags;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.SimpleWorldGenLayer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public final class AoAWorldGenLayers {
	public static IWorldGenLayer ABYSS;
	public static IWorldGenLayer BARATHOS;
	public static IWorldGenLayer BARATHOS_HELLSTONE;
	public static IWorldGenLayer DEEPLANDS;
	public static IWorldGenLayer CREEPONIA;
	public static IWorldGenLayer CRYSTEVIA;
	public static IWorldGenLayer MYSTERIUM;
	public static IWorldGenLayer GRECKON;
	public static IWorldGenLayer HAVEN;
	public static IWorldGenLayer IROMINE;
	public static IWorldGenLayer PRECASIA;
	public static IWorldGenLayer RUNANDOR;
	public static IWorldGenLayer SHYRELANDS;
	public static IWorldGenLayer LUNALUS;

	public static void init() {
		ABYSS = layer("aoa3_abyss", "abyss", AoABlocks.ABYSSAL_STONE);
		BARATHOS = layer("aoa3_barathos", "barathos", AoABlocks.BARON_STONE);
		BARATHOS_HELLSTONE = layer("aoa3_barathos_hellstone", "barathos", AoABlocks.HELLSTONE);
		DEEPLANDS = layer("aoa3_deeplands", "deeplands", AoABlocks.DENSE_STONE);
		CREEPONIA = new SimpleWorldGenLayer("aoa3_creeponia", () -> new TagMatchTest(TagKey.create(Registries.BLOCK, AdventOfAscension.id("base_stone_creeponia"))), Set.of(AdventOfAscension.id("creeponia")));
		CRYSTEVIA = layer("aoa3_crystevia", "crystevia", AoABlocks.CRYSTALLISED_ROCK);
		MYSTERIUM = layer("aoa3_mysterium", "mysterium", AoABlocks.FUNGAL_ROCK);
		GRECKON = layer("aoa3_greckon", "greckon", AoABlocks.GRECKON_STONE);
		HAVEN = layer("aoa3_haven", "haven", AoABlocks.WEIGHTLESS_STONE);
		IROMINE = layer("aoa3_iromine", "iromine", AoABlocks.IROSTONE);
		PRECASIA = layer("aoa3_precasia", "precasia", AoABlocks.PRECASIAN_LOWER_ROCK);
		RUNANDOR = layer("aoa3_runandor", "runandor", AoABlocks.RUNIC_STONE);
		SHYRELANDS = layer("aoa3_shyrelands", "shyrelands", AoABlocks.BRIGHT_ROCK);
		LUNALUS = new SimpleWorldGenLayer("aoa3_lunalus", () -> new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Set.of(AdventOfAscension.id("lunalus")));
	}

	private static IWorldGenLayer layer(String name, String dimension, RegistryObject<Block> stone) {
		return new SimpleWorldGenLayer(name, () -> blockMatch(stone), Set.of(AdventOfAscension.id(dimension)));
	}

	private static RuleTest blockMatch(RegistryObject<Block> block) {
		return new BlockMatchTest(block.get());
	}
}
