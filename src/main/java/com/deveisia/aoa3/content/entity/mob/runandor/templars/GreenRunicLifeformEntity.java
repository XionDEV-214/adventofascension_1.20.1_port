package com.deveisia.aoa3.content.entity.mob.runandor.templars;

import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class GreenRunicLifeformEntity extends RunicLifeformEntity {
	public GreenRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.GREEN_RUNIC_LIFEFORM.get(), templar);
	}

	public GreenRunicLifeformEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
