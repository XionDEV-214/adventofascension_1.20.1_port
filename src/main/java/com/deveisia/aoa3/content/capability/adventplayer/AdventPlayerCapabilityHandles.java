package com.deveisia.aoa3.content.capability.adventplayer;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface AdventPlayerCapabilityHandles extends INBTSerializable<CompoundTag> {
	ServerPlayerDataManager getPlayerData();
}
