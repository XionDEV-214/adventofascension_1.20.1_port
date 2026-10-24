package com.deveisia.aoa3.content.world.gen.feature.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.deveisia.aoa3.content.world.gen.feature.features.config.StructureFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public class StructurePieceGenFeature extends Feature<StructureFeatureConfig> {
	public StructurePieceGenFeature(Codec<StructureFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<StructureFeatureConfig> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		StructureFeatureConfig config = context.config();

		if (!config.requireGround || !reader.getBlockState(pos.below()).canBeReplaced())
			config.getTemplate(rand).placeInWorld(reader, pos, pos, config.getPlacementSettings(rand), rand, 2);

		return true;
	}
}
