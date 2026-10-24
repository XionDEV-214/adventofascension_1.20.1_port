package com.deveisia.aoa3.common.packet.packets;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public interface AoAPacket {
	void encode(FriendlyByteBuf buffer);
	void receiveMessage(Supplier<NetworkEvent.Context> context);
}
