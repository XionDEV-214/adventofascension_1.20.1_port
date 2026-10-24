package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.client.ClientOperations;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class GunRecoilPacket implements AoAPacket {
	private final float recoilAmount;
	private final int firingTime;

	public GunRecoilPacket(final float recoil, final int firingDelay) {
		recoilAmount = recoil;
		firingTime = firingDelay;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeFloat(recoilAmount);
		buffer.writeInt(firingTime);
	}

	public static GunRecoilPacket decode(FriendlyByteBuf buffer) {
		return new GunRecoilPacket(buffer.readFloat(), buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ClientOperations.addRecoil(recoilAmount, firingTime);
		});

		context.get().setPacketHandled(true);
	}
}
