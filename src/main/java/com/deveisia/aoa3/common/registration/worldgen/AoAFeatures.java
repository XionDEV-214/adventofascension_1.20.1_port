package com.deveisia.aoa3.common.registration.worldgen;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.world.gen.carver.carvers.ConfigurableCarver;
import com.deveisia.aoa3.content.world.gen.carver.config.ConfigurableCarverConfig;
import com.deveisia.aoa3.content.world.gen.feature.features.*;
import com.deveisia.aoa3.content.world.gen.feature.features.config.*;
import com.deveisia.aoa3.content.world.gen.feature.features.trees.*;
import com.deveisia.aoa3.content.world.gen.feature.placement.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class AoAFeatures {
	public static class Features {
		public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, AdventOfAscension.MOD_ID);

		public static final RegistryObject<Feature<BlockStateConfiguration>> NO_CONTEXT_BLOCK = register("no_context_block", () -> new NoContextBlockFeature(BlockStateConfiguration.CODEC));
		public static final RegistryObject<Feature<CappedColumnConfig>> CAPPED_COLUMN = register("capped_column", () -> new CappedColumnFeature(CappedColumnConfig.CODEC));
		public static final RegistryObject<Feature<ColumnConfig>> COLUMN = register("column", () -> new ColumnFeature(ColumnConfig.CODEC));
		public static final RegistryObject<Feature<LiquidDrainConfig>> LIQUID_DRAIN = register("liquid_drain", () -> new LiquidDrainFeature(LiquidDrainConfig.CODEC));
		public static final RegistryObject<Feature<BlockStateConfiguration>> BIOME_FRIENDLY_LAKE = register("biome_friendly_lake", () -> new BiomeFriendlyLake(BlockStateConfiguration.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> BLOCK_PILE = register("block_pile", () -> new BlockPileFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> CUBE = register("cube", () -> new CubeFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<StructureFeatureConfig>> STRUCTURE_PIECE = register("structure_piece", () -> new StructurePieceGenFeature(StructureFeatureConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> BENDY_COLUMN = register("bendy_column", () -> new BendyColumnFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> STALAGMITE = register("stalagmite", () -> new StalagmiteFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> RANDOM_BLOB = register("random_blob", () -> new RandomBlobFeature(MiscStateAndVariablesConfig.CODEC));

		public static final RegistryObject<AchonyTreeFeature> ACHONY_TREE = register("achony_tree", () -> new AchonyTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.ACHONY_SAPLING));
		public static final RegistryObject<BloodtwisterTreeFeature> BLOODTWISTER_TREE = register("bloodtwister_tree", () -> new BloodtwisterTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.BLOODTWISTER_SAPLING));
		public static final RegistryObject<BlueCelevusTreeFeature> BLUE_CELEVUS_TREE = register("blue_celevus_tree", () -> new BlueCelevusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.BLUE_CELEVUS_SAPLING));
		public static final RegistryObject<BlueHavenTreeFeature> BLUE_HAVEN_TREE = register("blue_haven_tree", () -> new BlueHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.BLUE_HAVEN_SAPLING));
		public static final RegistryObject<BrightShyreTreeFeature> BRIGHT_SHYRE_TREE = register("bright_shyre_tree", () -> new BrightShyreTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.BRIGHT_SHYRE_SAPLING));
		public static final RegistryObject<ChurryTreeFeature> CHURRY_TREE = register("churry_tree", () -> new ChurryTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.CHURRY_SAPLING));
		public static final RegistryObject<CreepTreeFeature> CREEP_TREE = register("creep_tree", () -> new CreepTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.CREEP_SAPLING));
		public static final RegistryObject<DawnwoodTreeFeature> DAWNWOOD_TREE = register("dawnwood_tree", () -> new DawnwoodTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.DAWNWOOD_SAPLING));
		public static final RegistryObject<EyebushTreeFeature> EYEBUSH_TREE = register("eyebush_tree", () -> new EyebushTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.EYEBUSH_SAPLING));
		public static final RegistryObject<EyeHangerTreeFeature> EYE_HANGER_TREE = register("eye_hanger_tree", () -> new EyeHangerTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.GREEN_CELEVUS_SAPLING));
		public static final RegistryObject<GreenCelevusTreeFeature> GREEN_CELEVUS_TREE = register("green_celevus_tree", () -> new GreenCelevusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.GREEN_CELEVUS_SAPLING));
		public static final RegistryObject<HauntedTreeFeature> HAUNTED_TREE = register("haunted_tree", () -> new HauntedTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.HAUNTED_SAPLING));
		public static final RegistryObject<InvertedAchonyTreeFeature> INVERTED_ACHONY_TREE = register("inverted_achony_tree", () -> new InvertedAchonyTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.ACHONY_SAPLING));
		public static final RegistryObject<InvertedChurryTreeFeature> INVERTED_CHURRY_TREE = register("inverted_churry_tree", () -> new InvertedChurryTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.CHURRY_SAPLING));
		public static final RegistryObject<IrodustTreeFeature> IRODUST_TREE = register("irodust_tree", () -> new IrodustTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.IRODUST_SAPLING));
		public static final RegistryObject<IrogoldTreeFeature> IROGOLD_TREE = register("irogold_tree", () -> new IrogoldTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.IROGOLD_SAPLING));
		public static final RegistryObject<LucalusTreeFeature> LUCALUS_TREE = register("lucalus_tree", () -> new LucalusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.LUCALUS_SAPLING));
		public static final RegistryObject<LuniciaTreeFeature> LUNICIA_TREE = register("lunicia_tree", () -> new LuniciaTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.LUNICIA_SAPLING));
		public static final RegistryObject<LunossoTreeFeature> LUNOSSO_TREE = register("lunosso_tree", () -> new LunossoTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.LUNOSSO_SAPLING));
		public static final RegistryObject<NormalShyreTreeFeature> NORMAL_SHYRE_TREE = register("normal_shyre_tree", () -> new NormalShyreTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.SHYRE_SAPLING));
		public static final RegistryObject<PinkHavenTreeFeature> PINK_HAVEN_TREE = register("pink_haven_tree", () -> new PinkHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.PINK_HAVEN_SAPLING));
		public static final RegistryObject<PurpleCelevusTreeFeature> PURPLE_CELEVUS_TREE = register("purple_celevus_tree", () -> new PurpleCelevusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.PURPLE_CELEVUS_SAPLING));
		public static final RegistryObject<PurpleHavenTreeFeature> PURPLE_HAVEN_TREE = register("purple_haven_tree", () -> new PurpleHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.PURPLE_HAVEN_SAPLING));
		public static final RegistryObject<RedCelevusTreeFeature> RED_CELEVUS_TREE = register("red_celevus_tree", () -> new RedCelevusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.RED_CELEVUS_SAPLING));
		public static final RegistryObject<RedHavenTreeFeature> RED_HAVEN_TREE = register("red_haven_tree", () -> new RedHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.RED_HAVEN_SAPLING));
		public static final RegistryObject<RunicTreeFeature> RUNIC_TREE = register("runic_tree", () -> new RunicTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.RUNIC_SAPLING));
		public static final RegistryObject<ShadowTreeFeature> SHADOW_TREE = register("shadow_tree", () -> new ShadowTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.SHADOW_SAPLING));
		public static final RegistryObject<StranglewoodTreeFeature> STRANGLEWOOD_TREE = register("stranglewood_tree", () -> new StranglewoodTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.STRANGLEWOOD_SAPLING));
		public static final RegistryObject<TurquoiseHavenTreeFeature> TURQUOISE_HAVEN_TREE = register("turquoise_haven_tree", () -> new TurquoiseHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.TURQUOISE_HAVEN_SAPLING));
		public static final RegistryObject<YellowCelevusTreeFeature> YELLOW_CELEVUS_TREE = register("yellow_celevus_tree", () -> new YellowCelevusTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.YELLOW_CELEVUS_SAPLING));
		public static final RegistryObject<YellowHavenTreeFeature> YELLOW_HAVEN_TREE = register("yellow_haven_tree", () -> new YellowHavenTreeFeature(BlockStateConfiguration.CODEC, AoABlocks.YELLOW_HAVEN_SAPLING));

		private static <C extends FeatureConfiguration, F extends Feature<C>> RegistryObject<F> register(String id, Supplier<F> feature) {
			return FEATURES.register(id, feature);
		}
	}

	public static class Carvers {
		public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(Registries.CARVER, AdventOfAscension.MOD_ID);

		public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> CONFIGURABLE_CARVER = CARVERS.register("configurable_carver", () -> new ConfigurableCarver(ConfigurableCarverConfig.CODEC));
	}

	public static class Placements {
		public static final DeferredRegister<PlacementModifierType<?>> PLACEMENTS = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, AdventOfAscension.MOD_ID);

		public static final RegistryObject<PlacementModifierType<AllXPlacement>> ALL_X = register("all_x", AllXPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<AllZPlacement>> ALL_Z = register("all_z", AllZPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<YFillPlacement>> Y_FILL = register("y_fill", YFillPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<YFillCeilingPlacement>> Y_FILL_CEILING = register("y_fill_ceiling", YFillCeilingPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<UpToSurfacePlacement>> UP_TO_SURFACE = register("up_to_surface", UpToSurfacePlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<UndergroundSurfacePlacement>> UNDERGROUND_SURFACE = register("underground_surface", UndergroundSurfacePlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<AboveHeightmapPlacement>> ABOVE_HEIGHTMAP = register("above_heightmap", AboveHeightmapPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<OffsetPlacement>> OFFSET = register("offset", OffsetPlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<ChancePlacement>> CHANCE = register("chance", ChancePlacement.CODEC);
		public static final RegistryObject<PlacementModifierType<HeightmapSpreadDoublePlacement>> HEIGHTMAP_SPREAD_DOUBLE = register("heightmap_spread_double", HeightmapSpreadDoublePlacement.CODEC);

		private static <P extends PlacementModifier> RegistryObject<PlacementModifierType<P>> register(String id, Codec<P> codec) {
			return PLACEMENTS.register(id, () -> () -> codec);
		}
	}
}
