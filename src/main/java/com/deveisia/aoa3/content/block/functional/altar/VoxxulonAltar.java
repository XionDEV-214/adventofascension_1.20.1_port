package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.VoxxulonEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class VoxxulonAltar extends BossAltarBlock {
	public VoxxulonAltar() {
		super(MapColor.TERRACOTTA_GREEN);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		VoxxulonEntity voxxulon = new VoxxulonEntity(AoAEntities.Mobs.VOXXULON.get(), player.level());

		voxxulon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level().addFreshEntity(voxxulon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VOXXULON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.VOX_PONDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.VILE_STONE.get();
	}
}
