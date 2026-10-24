package com.deveisia.aoa3.content.block.generation.plants;

import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DawnwoodBars extends BidirectionalStackablePlant {
	private static final VoxelShape SHAPE = Shapes.create(new AABB(0.375D, 0.0D, 0.375D, 0.625D, 1D, 0.625D));

	public DawnwoodBars() {
		super(new BlockUtil.CompactProperties(Material.REPLACEABLE_PLANT, MapColor.COLOR_BLACK).stats(1f, 0f).tool(ToolType.AXE).sound(SoundType.GRASS).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
