package com.deveisia.aoa3.content.entity.mob.lunalus;

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
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class VisulonEntity extends AoAFlyingMeleeMob {
	public VisulonEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.96875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_VISULON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_VISULAR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_VISULAR_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 2;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericFlyIdleController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_STRIKE));
	}
}
