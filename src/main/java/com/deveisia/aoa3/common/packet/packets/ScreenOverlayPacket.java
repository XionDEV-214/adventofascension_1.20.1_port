package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class ScreenOverlayPacket implements AoAPacket {
	private final int durationTicks;
	private final ResourceLocation overlay;

	public ScreenOverlayPacket(final ResourceLocation overlay, final int duration) {
		this.durationTicks = duration;
		this.overlay = overlay;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(overlay);
		buffer.writeInt(durationTicks);
	}

	public static ScreenOverlayPacket decode(FriendlyByteBuf buffer) {
		return new ScreenOverlayPacket(buffer.readResourceLocation(), buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ScreenOverlayRenderer.addOverlay(overlay, durationTicks);
		});

		context.get().setPacketHandled(true);
	}
}
