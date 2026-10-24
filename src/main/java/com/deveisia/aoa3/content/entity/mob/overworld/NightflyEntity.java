package com.deveisia.aoa3.content.entity.mob.overworld;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class NightflyEntity extends AoAFlyingMeleeMob {
	public NightflyEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NIGHTFLY_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NIGHTFLY_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NIGHTFLY_DEATH.get();
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	public void tick() {
		super.tick();

		if (getRandom().nextBoolean()) {
			setDeltaMovement(getDeltaMovement().multiply(1, 1.25, 1));
		}
		else {
			setDeltaMovement(getDeltaMovement().multiply(1, 0.75, 1));
		}
	}

	@Override
	protected int getAttackSwingDuration() {
		return 24;
	}

	@Override
	protected int getPreAttackTime() {
		return 16;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericFlyIdleController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_FLYING_BITE));
	}
}
