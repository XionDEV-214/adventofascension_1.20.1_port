package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.util.PlayerUtil;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class PatchouliBookOpenPacket implements AoAPacket {
	private final ResourceLocation book;

	public PatchouliBookOpenPacket(ResourceLocation id) {
		this.book = id;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(book);
	}

	public static PatchouliBookOpenPacket decode(FriendlyByteBuf buffer) {
		return new PatchouliBookOpenPacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			PlayerUtil.getAdventPlayer(context.get().getSender()).addPatchouliBook(book);
		});

		context.get().setPacketHandled(true);
	}
}
