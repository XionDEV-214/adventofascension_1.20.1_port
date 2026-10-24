package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.scheduling.async.KrorSpawnTask;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.concurrent.TimeUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar() {
		super(MapColor.COLOR_GRAY);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.CHARGING_TABLE.get()))
			return InteractionResult.FAIL;

		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		if (player instanceof ServerPlayer) {
			new KrorSpawnTask((ServerPlayer)player, blockPos.above()).schedule(1, TimeUnit.SECONDS);

			PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable(AoAEntities.Mobs.KROR.get().getDescriptionId() + ".start"));
		}
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		if (player.level().getBlockState(pos.above()).getBlock() != AoABlocks.CHARGING_TABLE.get() && player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable("message.feedback.krorAltar.chargingTable"));

			return false;
		}

		return WorldUtil.isWorld(player.level(), AoADimensions.DEEPLANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.BOULDER_DASH.get();
	}
}
