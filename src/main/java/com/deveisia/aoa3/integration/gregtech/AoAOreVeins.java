package com.deveisia.aoa3.integration.gregtech;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTOres;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.function.Consumer;

public final class AoAOreVeins {
	private static final RuleTest[] OVERWORLD_RULES = new RuleTest[] {new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)};
	private static final RuleTest[] DEEPSLATE_RULES = new RuleTest[] {new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)};
	private static final RuleTest[] NETHER_RULES = new RuleTest[] {new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)};

	public static void init() {
		create("serenadium_vein", vein -> vein
				.clusterSize(UniformInt.of(32, 40)).density(0.3f).weight(50)
				.layer(WorldGenLayers.STONE)
				.heightRangeUniform(10, 70)
				.biomes(BiomeTags.IS_OVERWORLD)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
								.layer(l -> l.weight(3).mat(AoAGTMaterials.SERENADIUM).size(2, 4))
								.layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 2))
								.layer(l -> l.weight(2).mat(GTMaterials.Goethite).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Hematite).size(1, 1))
								.build()))
				.surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(AoAGTMaterials.SERENADIUM).placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

		create("rosite_vein", vein -> vein
				.clusterSize(UniformInt.of(30, 38)).density(0.25f).weight(40)
				.layer(WorldGenLayers.STONE)
				.heightRangeUniform(0, 60)
				.biomes(BiomeTags.IS_OVERWORLD)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
								.layer(l -> l.weight(3).mat(AoAGTMaterials.ROSITE).size(2, 4))
								.layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Redstone).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Cinnabar).size(1, 1))
								.build()))
				.surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(AoAGTMaterials.ROSITE).placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

		create("runium_vein", vein -> vein
				.clusterSize(UniformInt.of(28, 36)).density(0.25f).weight(35)
				.layer(WorldGenLayers.STONE)
				.heightRangeUniform(0, 50)
				.biomes(BiomeTags.IS_OVERWORLD)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
								.layer(l -> l.weight(3).block(AoABlocks.RUNIUM_ORE).size(2, 4))
								.layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Lazurite).size(1, 1))
								.layer(l -> l.weight(1).mat(GTMaterials.Sodalite).size(1, 1))
								.build())));

		create("jade_vein", vein -> vein
				.clusterSize(UniformInt.of(25, 30)).density(0.25f).weight(30)
				.layer(WorldGenLayers.STONE)
				.heightRangeUniform(5, 45)
				.biomes(BiomeTags.IS_OVERWORLD)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
								.layer(l -> l.weight(3).mat(AoAGTMaterials.JADE).size(1, 3))
								.layer(l -> l.weight(2).mat(GTMaterials.Olivine).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Calcite).size(1, 2))
								.build()))
				.surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(AoAGTMaterials.JADE).placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

		create("aoa_amethyst_vein", vein -> vein
				.clusterSize(UniformInt.of(25, 30)).density(0.2f).weight(30)
				.layer(WorldGenLayers.DEEPSLATE)
				.heightRangeUniform(-50, 0)
				.biomes(BiomeTags.IS_OVERWORLD)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(DEEPSLATE_RULES)
								.layer(l -> l.weight(3).mat(AoAGTMaterials.AMETHYST).size(1, 3))
								.layer(l -> l.weight(2).mat(GTMaterials.Quartzite).size(1, 2))
								.layer(l -> l.weight(1).mat(AoAGTMaterials.SAPPHIRE).size(1, 1))
								.build()))
				.surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(AoAGTMaterials.AMETHYST).placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

		create("emberstone_vein", vein -> vein
				.clusterSize(UniformInt.of(32, 40)).density(0.25f).weight(40)
				.layer(WorldGenLayers.NETHERRACK)
				.heightRangeUniform(10, 110)
				.biomes(BiomeTags.IS_NETHER)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(NETHER_RULES)
								.layer(l -> l.weight(3).mat(AoAGTMaterials.EMBERSTONE).size(2, 4))
								.layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(1, 2))
								.layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(1, 1))
								.build()))
				.surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(AoAGTMaterials.EMBERSTONE).placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

		create("bloodstone_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.ABYSS, 0, 64, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.ABYSS))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.BLOODSTONE).size(2, 4))
								.build())));

		create("varsium_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.BARATHOS, 35, 74, 50)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.BARATHOS))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.VARSIUM).size(2, 4))
								.layer(l -> l.weight(1).mat(AoAGTMaterials.BARONYTE).size(1, 2))
								.build())));

		create("baronyte_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.BARATHOS, 15, 44, 50)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.BARATHOS))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.BARONYTE).size(2, 4))
								.layer(l -> l.weight(1).mat(AoAGTMaterials.VARSIUM).size(1, 1))
								.build())));

		create("blazium_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.BARATHOS_HELLSTONE, 0, 30, 40)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.BARATHOS_HELLSTONE))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.BLAZIUM).size(2, 4))
								.build())));

		create("charged_runium_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.DEEPLANDS, 0, 120, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.DEEPLANDS))
								.layer(l -> l.weight(3).block(AoABlocks.CHARGED_RUNIUM_ORE).size(2, 4))
								.build())));

		create("creeponia_gem_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.CREEPONIA, 0, 40, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.CREEPONIA))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.ORNAMYTE).size(1, 3))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.JEWELYTE).size(1, 3))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.GEMENYTE).size(1, 3))
								.build())));

		create("crystevia_gemstone_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.CRYSTEVIA, 10, 117, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.CRYSTEVIA))
								.layer(l -> l.weight(1).block(AoABlocks.BLUE_GEMSTONE_ORE).size(1, 3))
								.layer(l -> l.weight(1).block(AoABlocks.GREEN_GEMSTONE_ORE).size(1, 3))
								.layer(l -> l.weight(1).block(AoABlocks.PURPLE_GEMSTONE_ORE).size(1, 3))
								.layer(l -> l.weight(1).block(AoABlocks.RED_GEMSTONE_ORE).size(1, 3))
								.layer(l -> l.weight(1).block(AoABlocks.WHITE_GEMSTONE_ORE).size(1, 3))
								.layer(l -> l.weight(1).block(AoABlocks.YELLOW_GEMSTONE_ORE).size(1, 3))
								.build())));

		create("mystite_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.MYSTERIUM, 0, 100, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.MYSTERIUM))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.MYSTITE).size(2, 4))
								.build())));

		create("greckon_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.GRECKON, 0, 60, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.GRECKON))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.GHOULISH).size(2, 3))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.GHASTLY).size(2, 3))
								.build())));

		create("crystallite_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.HAVEN, 30, 70, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.HAVEN))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.CRYSTALLITE).size(2, 4))
								.build())));

		create("lyon_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.IROMINE, 0, 65, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.IROMINE))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.LYON).size(2, 4))
								.build())));

		create("bone_fragments_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.PRECASIA, 0, 40, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.PRECASIA))
								.layer(l -> l.weight(1).block(AoABlocks.CHESTBONE_FRAGMENTS_ORE).size(1, 2))
								.layer(l -> l.weight(1).block(AoABlocks.FOOTBONE_FRAGMENTS_ORE).size(1, 2))
								.layer(l -> l.weight(1).block(AoABlocks.LEGBONE_FRAGMENTS_ORE).size(1, 2))
								.layer(l -> l.weight(1).block(AoABlocks.SKULLBONE_FRAGMENTS_ORE).size(1, 2))
								.build())));

		create("elecanium_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.RUNANDOR, 0, 40, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.RUNANDOR))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.ELECANIUM).size(2, 4))
								.build())));

		create("shyrelands_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.SHYRELANDS, 65, 105, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.SHYRELANDS))
								.layer(l -> l.weight(3).mat(AoAGTMaterials.SHYRESTONE).size(2, 4))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.SHYREGEM).size(1, 2))
								.build())));

		create("lunalus_vein", vein -> dimensionVein(vein, AoAWorldGenLayers.LUNALUS, 5, 220, 60)
				.layeredVeinGenerator(generator -> generator
						.withLayerPattern(() -> GTLayerPattern.builder(rules(AoAWorldGenLayers.LUNALUS))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.AMETHYST).size(1, 3))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.JADE).size(1, 3))
								.layer(l -> l.weight(2).mat(AoAGTMaterials.ROSITE).size(1, 3))
								.layer(l -> l.weight(1).mat(AoAGTMaterials.SAPPHIRE).size(1, 2))
								.build())));
	}

	private static GTOreDefinition dimensionVein(GTOreDefinition vein, IWorldGenLayer layer, int minY, int maxY, int weight) {
		return vein.clusterSize(UniformInt.of(28, 38)).density(0.3f).weight(weight)
				.layer(layer)
				.heightRangeUniform(minY, maxY);
	}

	private static RuleTest[] rules(IWorldGenLayer layer) {
		return new RuleTest[] {layer.getTarget()};
	}

	private static void create(String id, Consumer<GTOreDefinition> config) {
		GTOres.create(AdventOfAscension.id(id), config);
	}
}
