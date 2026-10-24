package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class ExtractionDevice extends Block {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");

	public ExtractionDevice() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.PODZOL).stats(5f, 10f).noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(FILLED, false));
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!world.isClientSide) {
			BlockState topBlock = world.getBlockState(pos.above());

			if (!state.getValue(FILLED) && topBlock.getFluidState().isSource() && topBlock.getFluidState().is(FluidTags.LAVA)) {
				world.setBlockAndUpdate(pos, defaultBlockState().setValue(FILLED, true));
				world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
		if (world instanceof ServerLevel)
			tick(state, (ServerLevel)world, pos, world.random);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

		return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}
}
