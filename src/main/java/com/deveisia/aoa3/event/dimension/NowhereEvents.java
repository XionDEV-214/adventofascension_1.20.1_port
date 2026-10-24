package com.deveisia.aoa3.event.dimension;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public final class NowhereEvents {
	public static void doPlayerTick(final TickEvent.PlayerTickEvent ev) {
		Player pl = ev.player;

		pl.getAbilities().mayBuild = pl.isCreative();

		if (PlayerUtil.shouldPlayerBeAffected(pl)) {
			if (pl.getY() < 0) {
				pl.setPos(0, 202, 0);
				pl.fallDistance = -1;

				if (pl instanceof ServerPlayer)
					PlayerUtil.getAdventPlayer((ServerPlayer)pl).returnItemStorage();
			}

			if (pl.isFallFlying())
				pl.stopFallFlying();
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		ServerPlayer player = (ServerPlayer)ev.getEntity();

		ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		PlayerUtil.getAdventPlayer(player).returnItemStorage();
		player.gameMode.getGameModeForPlayer().updatePlayerAbilities(player.getAbilities());
	}
}
