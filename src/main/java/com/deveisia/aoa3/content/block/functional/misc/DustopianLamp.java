package com.deveisia.aoa3.content.block.functional.misc;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class DustopianLamp extends Block {
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	public DustopianLamp() {
		super(new BlockUtil.CompactProperties(Material.GLASS, MapColor.COLOR_GRAY).stats(5f, 10f).light(state -> state.getValue(LIT) ? 14 : 0).get());

		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
		return state.getValue(LIT) ? 14 : 0;
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.DARKLY_POWDER.get() && !state.getValue(LIT)) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				world.setBlockAndUpdate(pos, defaultBlockState().setValue(LIT, true));
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
