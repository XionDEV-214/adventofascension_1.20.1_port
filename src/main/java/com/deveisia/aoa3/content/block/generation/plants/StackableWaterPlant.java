package com.deveisia.aoa3.content.block.generation.plants;

import com.deveisia.aoa3.library.compat.Material;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;

public class StackableWaterPlant extends StackablePlant implements SimpleWaterloggedBlock {
	public StackableWaterPlant(Properties properties, Material... growthMaterial) {
		super(properties, growthMaterial);
	}

	public StackableWaterPlant(Material material, MapColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public StackableWaterPlant(MapColor mapColour, Material... growthMaterials) {
		super(mapColour, growthMaterials);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(BlockStateProperties.WATERLOGGED))
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}
}
