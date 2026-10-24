package com.deveisia.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StaticMushroomBlock extends MushroomBlock {
	public StaticMushroomBlock(Properties properties) {
		super(properties, TreeFeatures.HUGE_BROWN_MUSHROOM);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}
}
