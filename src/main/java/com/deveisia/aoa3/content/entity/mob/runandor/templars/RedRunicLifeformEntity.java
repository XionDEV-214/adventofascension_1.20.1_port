package com.deveisia.aoa3.content.entity.mob.runandor.templars;

import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class RedRunicLifeformEntity extends RunicLifeformEntity {
	public RedRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.RED_RUNIC_LIFEFORM.get(), templar);
	}

	public RedRunicLifeformEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}
}
