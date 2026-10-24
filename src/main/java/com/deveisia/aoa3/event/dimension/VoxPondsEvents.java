package com.deveisia.aoa3.event.dimension;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class VoxPondsEvents {
	public static void doPlayerTick(Player pl) {
		if (pl instanceof ServerPlayer && PlayerUtil.shouldPlayerBeAffected(pl) && !DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayer)pl)) {
			pl.hurt(AoADamageTypes.source(pl.level(), AoADamageTypes.VOX_PONDS), 1f);
			AdvancementUtil.completeAdvancement((ServerPlayer)pl, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "vox_ponds/oops"), "vox_ponds_atmosphere_damage");
		}
	}
}
