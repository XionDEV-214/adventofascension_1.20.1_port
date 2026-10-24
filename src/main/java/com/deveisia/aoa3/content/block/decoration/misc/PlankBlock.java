package com.deveisia.aoa3.content.block.decoration.misc;

import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class PlankBlock extends Block {
	public PlankBlock(MapColor materialColour) {
		super(new BlockUtil.CompactProperties(Material.WOOD, materialColour).stats(2f, 3f).get());
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 20;
	}
}
