package com.deveisia.aoa3.event.dimension;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.util.WorldUtil;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class LunalusEvents {
	public static boolean hasEnhancedAirControl(Player pl) {
		return WorldUtil.isWorld(pl.level(), AoADimensions.LUNALUS.key) && !(ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()));
	}

	public static void doPlayerTick(Player pl) {
		boolean hasDistortingArtifact = ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get());

		if (!pl.level().isClientSide() && PlayerUtil.shouldPlayerBeAffected(pl)) {
			if (pl.getY() <= -25 && !hasDistortingArtifact)
				pl.teleportTo(pl.getX(), 350, pl.getZ());

			if (!DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayer)pl))
				pl.hurt(AoADamageTypes.source(pl.level(), AoADamageTypes.SUFFOCATION), 1f);
		}

		if (hasDistortingArtifact)
			return;

		Vec3 motion = pl.getDeltaMovement();

		if (motion.y() < -0.01)
			pl.setDeltaMovement(motion.multiply(1, 0.85f, 1));
	}

	public static void doPlayerJump(Player pl) {
		if (ItemUtil.getStackFromHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) != null)
			return;

		pl.setDeltaMovement(pl.getDeltaMovement().add(0, 0.5f, 0));
	}

	public static void doPlayerLanding(Player pl, LivingFallEvent ev) {
		if (ItemUtil.getStackFromHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) != null && !ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()))
			return;

		ev.setDistance(Math.min(10, ev.getDistance()));
	}
}
