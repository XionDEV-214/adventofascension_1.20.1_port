package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.client.gui.hud.XpParticlesRenderer;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.player.skill.AoASkill;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class XpGainPacket implements AoAPacket {
	private final ResourceLocation skillId;
	private final float xp;
	private final boolean levelUp;

	public XpGainPacket(final ResourceLocation skillId, final float xp, boolean isLevelUp) {
		this.skillId = skillId;
		this.xp = xp;
		this.levelUp = isLevelUp;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skillId);
		buffer.writeFloat(xp);
		buffer.writeBoolean(levelUp);
	}

	public static XpGainPacket decode(FriendlyByteBuf buffer) {
		return new XpGainPacket(buffer.readResourceLocation(), buffer.readFloat(), buffer.readBoolean());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			if (AoAConfig.CLIENT.showXpParticles.get()) {
				AoASkill skill = AoASkills.getRegistry().getValue(skillId);

				if (skill != null)
					XpParticlesRenderer.addXpParticle(skill, xp, levelUp);
			}
		});

		context.get().setPacketHandled(true);
	}
}
