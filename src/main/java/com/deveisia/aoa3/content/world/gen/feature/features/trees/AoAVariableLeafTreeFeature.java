package com.deveisia.aoa3.content.world.gen.feature.features.trees;

import com.deveisia.aoa3.content.block.functional.plant.SaplingBlock;
import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public abstract class AoAVariableLeafTreeFeature extends AoATreeFeature {
	public AoAVariableLeafTreeFeature(Codec<BlockStateConfiguration> codec, Supplier<SaplingBlock> sapling) {
		super(codec, sapling);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		return generateTree(reader, rand, pos, getLeaf(), isWorldGen);
	}

	protected abstract boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen);

	protected abstract BlockState getLeaf();
}
