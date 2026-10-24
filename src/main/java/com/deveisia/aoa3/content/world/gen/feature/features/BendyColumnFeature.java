package com.deveisia.aoa3.content.world.gen.feature.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.deveisia.aoa3.content.world.gen.feature.features.config.MiscStateAndVariablesConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BendyColumnFeature extends Feature<MiscStateAndVariablesConfig> {
	public BendyColumnFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<MiscStateAndVariablesConfig> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		MiscStateAndVariablesConfig config = context.config();

		boolean placed = false;
		BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
		BlockStateProvider blockProvider = config.stateProvider;
		int height = config.count.getValue(rand);
		int count = 0;
		Direction moveDir = config.inverted ? Direction.DOWN : Direction.UP;
		Direction lastDir = null;

		while (count < height) {
			for (int i = 0; i < height - count && i < 1 + rand.nextInt(height); i++) {
				if (config.inverted ? placementPos.getY() < 0 : placementPos.getY() >= reader.dimensionType().logicalHeight())
					return placed;

				if (reader.isEmptyBlock(placementPos)) {
					reader.setBlock(placementPos, blockProvider.getState(rand, placementPos), 2);
					placementPos.move(moveDir);
					placed = true;
					count++;
				}
				else {
					return placed;
				}
			}

			Direction turn = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

			if (turn != lastDir) {
				placementPos.move(turn);
				placementPos.move(moveDir.getOpposite());
				lastDir = turn.getOpposite();
			}
		}

		return placed;
	}
}
