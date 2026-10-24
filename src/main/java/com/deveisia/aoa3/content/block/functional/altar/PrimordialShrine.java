package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.block.functional.misc.DustopianLamp;
import com.deveisia.aoa3.content.entity.boss.KajarosEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super(MapColor.TERRACOTTA_BLACK);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (world.getDifficulty() == Difficulty.PEACEFUL && player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable("message.feedback.spawnBoss.difficultyFail"));

			return InteractionResult.FAIL;
		}

		if (!world.isClientSide && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return InteractionResult.SUCCESS;
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		if (!WorldUtil.isWorld(player.level(), AoADimensions.DUSTOPIA.key))
			return false;

		return checkLamps(player.level(),
				pos.offset(5, 1, -3),
				pos.offset(4, 1, -1),
				pos.offset(3, 3, 1),
				pos.offset(1, 5, 1),
				pos.offset(-1, 5, 1),
				pos.offset(-3, 3, 1),
				pos.offset(-4, 1, -1),
				pos.offset(-5, 1, -3));
	}

	private boolean checkLamps(Level world, BlockPos... positions) {
		for (BlockPos pos : positions) {
			BlockState state = world.getBlockState(pos);

			if (state.getBlock() != AoABlocks.DUSTOPIAN_LAMP.get() || !state.getValue(DustopianLamp.LIT))
				return false;
		}

		return true;
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		Level world = player.level();
		BlockState lampOff = AoABlocks.DUSTOPIAN_LAMP.get().defaultBlockState().setValue(DustopianLamp.LIT, false);

		world.setBlockAndUpdate(RandomUtil.getRandomSelection(
				blockPos.offset(5, 1, -3),
				blockPos.offset(4, 1, -1),
				blockPos.offset(3, 3, 1),
				blockPos.offset(1, 5, 1),
				blockPos.offset(-1, 5, 1),
				blockPos.offset(-3, 3, 1),
				blockPos.offset(-4, 1, -1),
				blockPos.offset(-5, 1, -3)), lampOff);

		KajarosEntity kajaros = new KajarosEntity(AoAEntities.Mobs.KAJAROS.get(), player.level());

		kajaros.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level().addFreshEntity(kajaros);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.OKAZOR.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}
