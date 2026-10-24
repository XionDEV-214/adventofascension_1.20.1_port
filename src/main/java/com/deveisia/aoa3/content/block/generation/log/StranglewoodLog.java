package com.deveisia.aoa3.content.block.generation.log;

import com.deveisia.aoa3.common.registration.AoABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StranglewoodLog extends LogBlock {
	public static final VoxelShape VERTICAL_AABB = Shapes.create(new AABB(0.001D, 0.0D, 0.001D, 0.999D, 1.0D, 0.999D));
	public static final VoxelShape NORTH_SOUTH_AABB = Shapes.create(new AABB(0.0D, 0.001D, 0.001D, 1.0D, 0.999D, 0.999D));
	public static final VoxelShape EAST_WEST_AABB = Shapes.create(new AABB(0.001D, 0.001D, 0.0D, 0.999D, 0.999D, 1.0D));

	public StranglewoodLog() {
		super(MapColor.COLOR_BROWN, MapColor.TERRACOTTA_BROWN, AoABlocks.STRIPPED_STRANGLEWOOD_LOG);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		switch(state.getValue(AXIS)) {
			case X:
				return NORTH_SOUTH_AABB;
			case Z:
				return EAST_WEST_AABB;
			case Y:
			default:
				return VERTICAL_AABB;
		}
	}
}
