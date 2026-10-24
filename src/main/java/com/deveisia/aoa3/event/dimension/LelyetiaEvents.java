package com.deveisia.aoa3.event.dimension;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import net.minecraft.world.entity.player.Player;

public class LelyetiaEvents {
	public static void doPlayerTick(Player pl) {
		if (ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()))
			return;

		if (pl.getY() <= -25 && PlayerUtil.shouldPlayerBeAffected(pl))
			pl.teleportTo(pl.getX(), pl.level().getMaxBuildHeight(), pl.getZ());
	}
}
