package com.deveisia.aoa3.content.entity.mob.overworld.bigday;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LeafyGiantEntity extends AoAMeleeMob {
	public LeafyGiantEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 3f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GIANT_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GIANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			double resist = 1;
			AttributeInstance attrib = ((LivingEntity)target).getAttribute(Attributes.KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getValue();

			((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 0, true, true));
			target.push(getDeltaMovement().x() * 10.5f * resist, 0.5 * resist, getDeltaMovement().z() * 10.5 * resist);
			target.hurtMarked = true;
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericWalkController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SLAM));
	}
}
