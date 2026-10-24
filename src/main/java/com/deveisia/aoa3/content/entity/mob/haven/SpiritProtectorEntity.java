package com.deveisia.aoa3.content.entity.mob.haven;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.SpiritualShotEntity;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SpiritProtectorEntity extends AoARangedMob {
	public SpiritProtectorEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_SPIRIT_PROTECTOR_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new SpiritualShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
