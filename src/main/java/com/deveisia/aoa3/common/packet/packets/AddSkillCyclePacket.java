package com.deveisia.aoa3.common.packet.packets;

import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class AddSkillCyclePacket implements AoAPacket {
	private final AoASkill skill;

	public AddSkillCyclePacket(ResourceLocation skillId) {
		this.skill = AoASkills.getSkill(skillId);
	}

	public AddSkillCyclePacket(AoASkill skill) {
		this.skill = skill;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skill == null ? ResourceLocation.fromNamespaceAndPath("", "") : RegistryUtil.getId(skill));
	}

	public static AddSkillCyclePacket decode(FriendlyByteBuf buffer) {
		return new AddSkillCyclePacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (skill != null)
			context.get().enqueueWork(() -> PlayerUtil.getAdventPlayer(context.get().getSender()).getSkill(skill).addCycle());

		context.get().setPacketHandled(true);
	}
}
