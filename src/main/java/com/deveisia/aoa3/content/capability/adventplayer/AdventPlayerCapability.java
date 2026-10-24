package com.deveisia.aoa3.content.capability.adventplayer;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class AdventPlayerCapability implements AdventPlayerCapabilityHandles {
	private final ServerPlayerDataManager playerDataManager;

	public AdventPlayerCapability() {
		this.playerDataManager = null;
	}

	public AdventPlayerCapability(ServerPlayer pl) {
		this.playerDataManager = new ServerPlayerDataManager(pl);
	}

	@Override
	public ServerPlayerDataManager getPlayerData() {
		return playerDataManager;
	}

	@Override
	public CompoundTag serializeNBT() {
		return playerDataManager.savetoNbt(false);
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		playerDataManager.loadFromNbt(nbt);
	}
}
