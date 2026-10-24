package com.deveisia.aoa3.content.entity.mob.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.BaneEntity;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BigBaneCloneEntity extends AoAMeleeMob {
	public BigBaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BIG_BANE_CLONE.get(), bane.level());

		moveTo(bane.getX(), bane.getY(), bane.getZ(), random.nextFloat() * 360, 0);
	}

	public BigBaneCloneEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.3125f;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			WorldUtil.createExplosion(this, level(), 6f);
			discard();
		}
	}
}
