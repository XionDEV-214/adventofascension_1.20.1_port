package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.CrystocoreEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class PowerStation extends BossAltarBlock {
	public PowerStation() {
		super(MapColor.COLOR_YELLOW);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		CrystocoreEntity crystocore = new CrystocoreEntity(AoAEntities.Mobs.CRYSTOCORE.get(), player.level());

		crystocore.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level().addFreshEntity(crystocore);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CRYSTOCORE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.CRYSTEVIA.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GIANT_CRYSTAL.get();
	}
}
