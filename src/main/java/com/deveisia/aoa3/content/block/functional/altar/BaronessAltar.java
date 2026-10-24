package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.BaronessEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;

public class BaronessAltar extends BossAltarBlock {
	public BaronessAltar() {
		super(MapColor.COLOR_PURPLE);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		BaronessEntity baroness = new BaronessEntity(AoAEntities.Mobs.BARONESS.get(), player.level());

		for (Player pl : player.level().getEntitiesOfClass(Player.class, new AABB(blockPos).inflate(5), entity -> entity != null && entity.isAlive())) {

			pl.push(Math.signum(pl.getX() - ((double)blockPos.getX() + 0.5d)) * 10, 0.1, Math.signum(pl.getZ() - ((double)blockPos.getZ() + 0.5d)) * 10);
			pl.hurtMarked = true;
		}

		baroness.teleportTo(blockPos.getX() + 0.5, blockPos.above().getY(), blockPos.getZ() + 0.5);
		player.level().addFreshEntity(baroness);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.BARONESS.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.BARATHOS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.WARLOCK_GEM.get();
	}
}
