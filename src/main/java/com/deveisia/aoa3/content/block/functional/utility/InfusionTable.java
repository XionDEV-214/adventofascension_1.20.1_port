package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.container.InfusionTableContainer;
import com.deveisia.aoa3.content.item.misc.InfusionStone;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class InfusionTable extends Block {
	public InfusionTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_PURPLE).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player instanceof ServerPlayer) {
			ItemStack stack = player.getItemInHand(hand);
			Item item = stack.getItem();

			if (item instanceof InfusionStone) {
				InfusionStone stone = (InfusionStone)item;
				int count = stack.getCount();

				int powerStoneCount = 0;

				for (int i = 0; i < count; i++) {
					if (RandomUtil.oneInNChance(3))
						powerStoneCount++;
				}

				if (!player.isCreative()) {
					if (powerStoneCount > 0) {
						player.setItemInHand(hand, new ItemStack(stone.getPowerStone(), powerStoneCount));
					}
					else {
						player.setItemInHand(hand, ItemStack.EMPTY);
					}
				}
				else {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(stone.getPowerStone(), powerStoneCount));
				}
			}
			else {
				InfusionTableContainer.openContainer((ServerPlayer)player, pos);
			}
		}

		return InteractionResult.SUCCESS;
	}
}
