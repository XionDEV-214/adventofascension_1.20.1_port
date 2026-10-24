package com.deveisia.aoa3.content.block.functional.altar;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.boss.ElusiveEntity;
import com.deveisia.aoa3.content.entity.mob.misc.ElusiveCloneEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.MapColor;

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar() {
		super(MapColor.COLOR_PURPLE);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		ElusiveEntity elusive = new ElusiveEntity(AoAEntities.Mobs.ELUSIVE.get(), player.level());
		ElusiveCloneEntity clone = new ElusiveCloneEntity(elusive);
		double posX = (int)(blockPos.getX() + player.getLookAngle().x * -10);
		double posZ = (int)(blockPos.getZ() + player.getLookAngle().z * -10);

		clone.moveTo(posX, player.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(posX, 64, posZ)).getY(), posZ, player.yHeadRot, 0);

		posX += player.getRandom().nextBoolean() ? 10 + player.getRandom().nextInt(10) : -10 - player.getRandom().nextInt(10);
		posZ += player.getRandom().nextBoolean() ? 10 + player.getRandom().nextInt(10) : -10 - player.getRandom().nextInt(10);

		elusive.moveTo(posX, player.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(posX, 64, posZ)).getY(), posZ, 0, 0);
		player.level().addFreshEntity(elusive);
		player.level().addFreshEntity(clone);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.ELUSIVE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.ABYSS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.STARING_EYE.get();
	}
}
