package com.deveisia.aoa3.content.world.gen.feature.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.deveisia.aoa3.content.world.gen.feature.features.config.MiscStateAndVariablesConfig;
import com.mojang.serialization.Codec;
import java.util.BitSet;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public class StalagmiteFeature extends Feature<MiscStateAndVariablesConfig> {
	public StalagmiteFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<MiscStateAndVariablesConfig> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		MiscStateAndVariablesConfig config = context.config();

		int height = config.count.getValue(rand);
		int width = config.x.getValue(rand);

		BlockState state = config.stateProvider.getState(rand, pos);
		BitSet placementPositions = new BitSet(width * width);

		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				for (int z = 0; z < width; z++) {
					if (rand.nextFloat() < width / (((float)height - (i + 1)) * width))
						placementPositions.set(x << (int)(Math.log(width) / Math.log(2) + 1) | z);

					if (!placementPositions.get(x << (int)(Math.log(width) / Math.log(2) + 1) | z)) {
						BlockPos placePos = pos.offset(x, i, z);

						reader.setBlock(placePos, state, 2);
					}
				}
			}
		}

		return true;
	}
}
