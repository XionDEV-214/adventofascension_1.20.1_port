package com.deveisia.aoa3.event.custom.events;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class PlayerChangeXpEvent extends PlayerEvent {
	private final float rawXpGain;
	private final float xpGain;
	private float newXpGain;
	private final AoASkill.Instance skill;
	private final boolean isNaturalGain;

	public PlayerChangeXpEvent(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, float rawXpGain, float xpAfterModifiers, boolean isNaturalGainMethod) {
		super(playerDataManager.player());

		this.rawXpGain = rawXpGain;
		this.xpGain = xpAfterModifiers;
		this.newXpGain = xpGain;
		this.skill = skill;
		this.isNaturalGain = isNaturalGainMethod;
	}

	public float getRawXpGain() {
		return this.rawXpGain;
	}

	public float getOriginalXpGain() {
		return this.xpGain;
	}

	public float getNewXpGain() {
		return this.newXpGain;
	}

	public AoASkill.Instance getSkill() {
		return skill;
	}

	public void setXpGain(float newXpAmount) {
		this.newXpGain = newXpAmount;
	}

	public boolean isNaturallyGainedXp() {
		return isNaturalGain;
	}
}
