package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.HiveKingEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;

public class HiveSpawner extends BossAltarBlock {
	public HiveSpawner() {
		super(MapColor.TERRACOTTA_RED);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		HiveKingEntity hiveKing = new HiveKingEntity(player.level());

		hiveKing.moveTo(blockPos.getX() + 0.99, blockPos.getY() + 1.050000001d, blockPos.getZ() + 0.99, 0, 0);
		player.level().addFreshEntity(hiveKing);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.HIVE_KING.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.BARATHOS.key) && player.level().getEntitiesOfClass(HiveKingEntity.class, new AABB(pos).inflate(20)).isEmpty();
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.HIVE_EGG.get();
	}
}
