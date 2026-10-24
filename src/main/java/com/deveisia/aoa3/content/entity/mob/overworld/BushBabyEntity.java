package com.deveisia.aoa3.content.entity.mob.overworld;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BushBabyEntity extends AoAMeleeMob {
	public BushBabyEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.46875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_BUSH_BABY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_BUSH_BABY_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_BUSH_BABY_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericWalkIdleController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
