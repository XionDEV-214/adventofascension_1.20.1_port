package com.deveisia.aoa3.content.entity.mob.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.HiveKingEntity;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class HiveWorkerEntity extends AoAMeleeMob {
	public HiveWorkerEntity(HiveKingEntity hiveKing) {
		this(AoAEntities.Mobs.HIVE_WORKER.get(), hiveKing.level());

		moveTo(hiveKing.getX(), hiveKing.getY(), hiveKing.getZ(), random.nextFloat() * 360, 1);
	}

	public HiveWorkerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.1625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PARASECT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PARASECT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PARASECT_HURT.get();
	}
}
