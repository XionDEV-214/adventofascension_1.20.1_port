package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.VisualentEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class VisualentAltar extends BossAltarBlock {
	public VisualentAltar() {
		super(MapColor.TERRACOTTA_PURPLE);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		VisualentEntity visualent = new VisualentEntity(AoAEntities.Mobs.VISUALENT.get(), player.level());

		visualent.teleportTo(blockPos.getX() + 0.5, blockPos.above().getY(), blockPos.getZ() + 0.5);
		player.level().addFreshEntity(visualent);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VISUALENT.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.LUNALUS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.OBSERVING_EYE.get();
	}
}
