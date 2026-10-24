package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.container.DivineStationContainer;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class DivineStation extends Block {
	public DivineStation() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_CYAN).stats(5f, 10f).sound(SoundType.GLASS).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (player instanceof ServerPlayer)
			DivineStationContainer.openContainer((ServerPlayer)player, pos);

		return InteractionResult.SUCCESS;
	}
}
