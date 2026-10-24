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

public class Stranglewood extends LogBlock {
	public static final VoxelShape BARK_AABB = Shapes.create(new AABB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D));

	public Stranglewood() {
		super(MapColor.COLOR_BROWN, AoABlocks.STRIPPED_STRANGLEWOOD);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return BARK_AABB;
	}
}
