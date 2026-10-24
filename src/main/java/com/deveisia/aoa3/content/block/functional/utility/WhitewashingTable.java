package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.container.WhitewashingTableContainer;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class WhitewashingTable extends Block {
	public WhitewashingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.TERRACOTTA_WHITE).stats(5f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player instanceof ServerPlayer)
			WhitewashingTableContainer.openContainer((ServerPlayer)player, pos);

		return InteractionResult.SUCCESS;
	}
}
