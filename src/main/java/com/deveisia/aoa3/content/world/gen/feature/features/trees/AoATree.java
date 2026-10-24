package com.deveisia.aoa3.content.world.gen.feature.features.trees;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.resources.ResourceKey;

public class AoATree extends AbstractTreeGrower {
	private final Supplier<? extends AoATreeFeature> treeFeature;

	public AoATree(Supplier<? extends AoATreeFeature> treeFeature) {
		this.treeFeature = treeFeature;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
		return null;
	}

	@Override
	public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource rand) {
		if (treeFeature == null)
			return false;

		if (treeFeature.get().generate(world, rand, pos)) {
			return true;
		}
		else {
			world.setBlock(pos, state, 4);

			return false;
		}
	}
}
