package com.deveisia.aoa3.integration.gregtech;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class AoAOrePrefixes {
	public static final List<TagPrefix> ALL = new ArrayList<>();
	public static final List<StoneType> STONE_TYPES = new ArrayList<>();

	public static TagPrefix ABYSSAL_STONE;
	public static TagPrefix BARON_STONE;
	public static TagPrefix HELLSTONE;
	public static TagPrefix DENSE_STONE;
	public static TagPrefix CREEP_STONE;
	public static TagPrefix CRYSTALLISED_ROCK;
	public static TagPrefix FUNGAL_ROCK;
	public static TagPrefix GRECKON_STONE;
	public static TagPrefix WEIGHTLESS_STONE;
	public static TagPrefix IROSTONE;
	public static TagPrefix PRECASIAN_LOWER_ROCK;
	public static TagPrefix RUNIC_STONE;
	public static TagPrefix BRIGHT_ROCK;

	public static void init() {
		ABYSSAL_STONE = stone("abyssal_stone", "Abyssal", AoABlocks.ABYSSAL_STONE, () -> AoAGTMaterials.ABYSSAL_STONE, MapColor.NETHER, SoundType.STONE);
		BARON_STONE = stone("baron_stone", "Baron", AoABlocks.BARON_STONE, () -> AoAGTMaterials.BARON_STONE, MapColor.TERRACOTTA_CYAN, SoundType.STONE);
		HELLSTONE = stone("hellstone", "Hellstone", AoABlocks.HELLSTONE, () -> AoAGTMaterials.HELLSTONE, MapColor.FIRE, SoundType.NETHER_ORE);
		DENSE_STONE = stone("dense_stone", "Dense", AoABlocks.DENSE_STONE, () -> AoAGTMaterials.DENSE_STONE, MapColor.STONE, SoundType.DEEPSLATE);
		CREEP_STONE = stone("creep_stone", "Creep", AoABlocks.CREEP_STONE, () -> AoAGTMaterials.CREEP_STONE, MapColor.COLOR_GREEN, SoundType.STONE);
		CRYSTALLISED_ROCK = stone("crystallised_rock", "Crystallised", AoABlocks.CRYSTALLISED_ROCK, () -> AoAGTMaterials.CRYSTALLISED_ROCK, MapColor.COLOR_PURPLE, SoundType.AMETHYST);
		FUNGAL_ROCK = stone("fungal_rock", "Fungal", AoABlocks.FUNGAL_ROCK, () -> AoAGTMaterials.FUNGAL_ROCK, MapColor.TERRACOTTA_BLUE, SoundType.STONE);
		GRECKON_STONE = stone("greckon_stone", "Greckon", AoABlocks.GRECKON_STONE, () -> AoAGTMaterials.GRECKON_STONE, MapColor.TERRACOTTA_PURPLE, SoundType.STONE);
		WEIGHTLESS_STONE = stone("weightless_stone", "Weightless", AoABlocks.WEIGHTLESS_STONE, () -> AoAGTMaterials.WEIGHTLESS_STONE, MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.STONE);
		IROSTONE = stone("irostone", "Irostone", AoABlocks.IROSTONE, () -> AoAGTMaterials.IROSTONE, MapColor.COLOR_GRAY, SoundType.STONE);
		PRECASIAN_LOWER_ROCK = stone("precasian_lower_rock", "Precasian", AoABlocks.PRECASIAN_LOWER_ROCK, () -> AoAGTMaterials.PRECASIAN_LOWER_ROCK, MapColor.TERRACOTTA_GREEN, SoundType.STONE);
		RUNIC_STONE = stone("runic_stone", "Runic", AoABlocks.RUNIC_STONE, () -> AoAGTMaterials.RUNIC_STONE, MapColor.COLOR_BLUE, SoundType.STONE);
		BRIGHT_ROCK = stone("bright_rock", "Bright", AoABlocks.BRIGHT_ROCK, () -> AoAGTMaterials.BRIGHT_ROCK, MapColor.TERRACOTTA_YELLOW, SoundType.STONE);
	}

	private static TagPrefix stone(String id, String langPrefix, RegistryObject<Block> stoneBlock, Supplier<Material> stoneMaterial, MapColor mapColour, SoundType sound) {
		Supplier<BlockState> state = () -> stoneBlock.isPresent()
				? stoneBlock.get().defaultBlockState()
				: Blocks.AIR.defaultBlockState();
		TagPrefix prefix = TagPrefix.oreTagPrefix(id, BlockTags.MINEABLE_WITH_PICKAXE)
				.langValue(langPrefix + " %s Ore")
				.registerOre(state, stoneMaterial,
						BlockBehaviour.Properties.of().mapColor(mapColour).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(sound),
						AdventOfAscension.id("block/" + id), false, false, true);

		ALL.add(prefix);
		STONE_TYPES.add(new StoneType(id, prefix, stoneBlock, stoneMaterial));

		return prefix;
	}

	public static List<StoneType> getStoneTypes() {
		return Collections.unmodifiableList(STONE_TYPES);
	}

	public record StoneType(String id, TagPrefix prefix, RegistryObject<Block> block, Supplier<Material> material) {}
}
