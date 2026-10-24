package com.deveisia.aoa3.content.entity.mob.runandor.templars;

import com.deveisia.aoa3.common.registration.AoAItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BlueRuneTemplarEntity extends RuneTemplarEntity {
	public BlueRuneTemplarEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new BlueRunicLifeformEntity(this);
	}

	@Override
	protected Item getActivationRune() {
		return AoAItems.WATER_RUNE.get();
	}
}
