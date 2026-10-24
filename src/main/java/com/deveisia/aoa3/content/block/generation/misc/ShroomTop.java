package com.deveisia.aoa3.content.block.generation.misc;

import com.deveisia.aoa3.content.block.WaterloggableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShroomTop extends WaterloggableBlock {
	private static final VoxelShape BOTTOM_SHAPE = box(5, 0, 5, 11, 6, 11);
	private static final VoxelShape TOP_SHAPE = box(0, 6, 0, 16, 16, 16);
	private static final VoxelShape SHAPE = Shapes.or(BOTTOM_SHAPE, TOP_SHAPE);

	public ShroomTop(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
