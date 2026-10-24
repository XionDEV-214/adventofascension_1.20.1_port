package com.deveisia.aoa3.content.entity.mob.runandor;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.RandomUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BouncerEntity extends AoAMeleeMob {
	private int jumpCooldown;

	public BouncerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		random.setSeed(getUUID().getMostSignificantBits());
		jumpCooldown = RandomUtil.randomNumberBetween(40, 120);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.1875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_BOUNCER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_BOUNCER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_BOUNCER_HURT.get();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource fallSource) {
		return false;
	}

	@Override
	public float getJumpPower() {
		return jumpCooldown == 0 ? 1.2f : super.getJumpPower();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!isAlive())
			return;

		if (jumpCooldown > 0) {
			jumpCooldown--;
		}
		else if (onGround()) {
			jumpFromGround();
			jumpCooldown = 70;
		}
	}
}
