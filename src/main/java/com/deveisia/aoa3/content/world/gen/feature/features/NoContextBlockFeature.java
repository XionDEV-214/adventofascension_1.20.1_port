package com.deveisia.aoa3.content.world.gen.feature.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class NoContextBlockFeature extends Feature<BlockStateConfiguration> {
	public NoContextBlockFeature(Codec<BlockStateConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		BlockStateConfiguration config = context.config();

		reader.setBlock(pos, config.state, 2);

		return false;
	}
}
