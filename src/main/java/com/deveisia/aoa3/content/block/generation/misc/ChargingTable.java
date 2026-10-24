package com.deveisia.aoa3.content.block.generation.misc;

import com.deveisia.aoa3.content.block.WaterloggableBlock;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChargingTable extends WaterloggableBlock {
	private static final VoxelShape BOTTOM_SHAPE = box(0, 0, 0, 16, 2, 16);
	private static final VoxelShape MID_SHAPE = box(3, 2, 3, 13, 10, 13);
	private static final VoxelShape TOP_SHAPE = box(5, 10, 5, 11, 16, 11);
	private static final VoxelShape SHAPE = Shapes.or(BOTTOM_SHAPE, MID_SHAPE, TOP_SHAPE);

	public ChargingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_LIGHT_GRAY).stats(5f, 4f).harvestTool(ToolType.PICKAXE).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
