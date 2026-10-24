package com.deveisia.aoa3.content.world.gen.feature.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.deveisia.aoa3.content.world.gen.feature.features.config.MiscStateAndVariablesConfig;
import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public class RandomBlobFeature extends Feature<MiscStateAndVariablesConfig> {
	public RandomBlobFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<MiscStateAndVariablesConfig> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		MiscStateAndVariablesConfig config = context.config();

		BlockState state = config.stateProvider.getState(rand, pos);
		int sizeX = config.x.getValue(rand);
		int sizeY = config.y.getValue(rand);
		int sizeZ = config.z.getValue(rand);
		float chance = 1 / (float)config.count.getValue(rand);
		Predicate<Float> testPredicate = config.inverted ? val -> rand.nextFloat() > val : val -> rand.nextFloat() < val;

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y <= sizeY; y++) {
				for (int z = 0; z <= sizeZ; z++) {
					if (testPredicate.test(chance))
						reader.setBlock(pos.offset(x, y, z), state, 2);
				}
			}
		}

		return true;
	}
}
