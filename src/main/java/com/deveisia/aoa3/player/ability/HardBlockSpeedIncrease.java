package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HardBlockSpeedIncrease extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK_SPEED};

	public HardBlockSpeedIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARD_BLOCK_SPEED_INCREASE.get(), skill, data);
	}

	public HardBlockSpeedIncrease(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HARD_BLOCK_SPEED_INCREASE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHarvestSpeedCheck(PlayerEvent.BreakSpeed ev) {
		float hardness = ev.getState().getDestroySpeed(ev.getEntity().level(), ev.getPosition().orElse(BlockPos.ZERO));

		if (hardness > 2)
			ev.setNewSpeed(ev.getNewSpeed() * (1 + (getScaledValue() * ((hardness - 2) / 48f))));
	}
}
