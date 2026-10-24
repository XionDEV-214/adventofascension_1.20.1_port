package com.deveisia.aoa3.content.entity.mob.runandor.templars;

import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BlueRunicLifeformEntity extends RunicLifeformEntity {
	public BlueRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.BLUE_RUNIC_LIFEFORM.get(), templar);
	}

	public BlueRunicLifeformEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
