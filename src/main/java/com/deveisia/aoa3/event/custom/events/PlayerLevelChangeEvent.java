package com.deveisia.aoa3.event.custom.events;

import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLevelChangeEvent extends PlayerEvent {
	private final int oldLevel;
	private final AoASkill.Instance skill;
	private final boolean wasNaturallyChanged;

	public PlayerLevelChangeEvent(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		super(playerDataManager.player());

		this.oldLevel = oldLevel;
		this.skill = skill;
		this.wasNaturallyChanged = wasNaturallyChanged;
	}

	public int getOldLevel() {
		return oldLevel;
	}

	public int getNewLevel() {
		return skill.getLevel(true);
	}

	public AoASkill.Instance getSkill() {
		return skill;
	}

	public boolean wasNaturalLevelChange() {
		return wasNaturallyChanged;
	}
}
