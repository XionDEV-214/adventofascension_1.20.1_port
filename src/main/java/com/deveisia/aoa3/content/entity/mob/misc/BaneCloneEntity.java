package com.deveisia.aoa3.content.entity.mob.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.BaneEntity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BaneCloneEntity extends AoAMeleeMob {
	public BaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BANE_CLONE.get(), bane.level());

		moveTo(bane.getX(), bane.getY(), bane.getZ(), random.nextFloat() * 360, 0);
	}

	public BaneCloneEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.1875f;
	}

}
