package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.library.builder.SoundBuilder;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AoASoundBuilderPacket implements AoAPacket {
	private final SoundBuilder soundBuilder;

	public AoASoundBuilderPacket(SoundBuilder soundBuilder) {
		this.soundBuilder = soundBuilder;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		soundBuilder.toNetwork(buffer);
	}

	public static AoASoundBuilderPacket decode(FriendlyByteBuf buffer) {
		return new AoASoundBuilderPacket(SoundBuilder.fromNetwork(buffer));
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			soundBuilder.resolveClientData();
			soundBuilder.play();
		});
		context.get().setPacketHandled(true);
	}
}
