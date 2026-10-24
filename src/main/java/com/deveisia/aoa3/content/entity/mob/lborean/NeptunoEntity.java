package com.deveisia.aoa3.content.entity.mob.lborean;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAWaterMeleeMob;
import com.deveisia.aoa3.util.DamageUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;

public class NeptunoEntity extends AoAWaterMeleeMob {
	public NeptunoEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.71875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NEPTUNO_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NEPTUNO_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NEPTUNO_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 4, 1.5f, 4);
	}

	@Override
	protected int getAttackSwingDuration() {
		return 13;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericSwimIdleController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SWING));
	}
}
