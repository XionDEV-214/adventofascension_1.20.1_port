package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class HauntingTable extends Block {
	public HauntingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.TERRACOTTA_PURPLE).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.GHOULASM.get()) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.PRIMED_GHOULASM.get()));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_HAUNTING_TABLE_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
