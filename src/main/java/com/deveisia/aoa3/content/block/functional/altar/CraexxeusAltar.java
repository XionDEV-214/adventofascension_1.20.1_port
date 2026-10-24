package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.CraexxeusEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CraexxeusAltar extends BossAltarBlock {
	public CraexxeusAltar() {
		super(MapColor.COLOR_CYAN);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		CraexxeusEntity craexxeus = new CraexxeusEntity(AoAEntities.Mobs.CRAEXXEUS.get(), player.level());

		int offsetX = player.getRandom().nextBoolean() ? -11 : 11;
		int offsetZ = player.getRandom().nextBoolean() ? -11 : 11;

		craexxeus.moveTo(blockPos.getX() + offsetX, blockPos.getY() + 5, blockPos.getZ() + offsetZ, 0, 0);
		player.level().addFreshEntity(craexxeus);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CRAEXXEUS.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.SHYRELANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.ANCIENT_RING.get();
	}
}
