package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public abstract class BossAltarBlock extends Block {
	public BossAltarBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	public BossAltarBlock(MapColor mapColour) {
		this(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(35f, 1000f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (getActivationItem() != null && heldItem.getItem() != getActivationItem())
			return InteractionResult.PASS;

		if (player instanceof ServerPlayer) {
			if (getActivationItem() == null || (heldItem.getItem() == getActivationItem())) {
				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable("message.feedback.spawnBoss.difficultyFail"));
					return InteractionResult.FAIL;
				}
				else if (checkActivationConditions(player, hand, state, pos)) {
					if (!player.isCreative())
						heldItem.shrink(1);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return InteractionResult.SUCCESS;
	}

	@Nullable
	protected abstract Item getActivationItem();

	protected abstract void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos);

	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return true;
	}

	protected void sendSpawnMessage(Player player, MutableComponent msg, BlockPos pos) {
		PlayerUtil.messageAllPlayersInRange(msg, player.level(), pos, 50);
	}
}
