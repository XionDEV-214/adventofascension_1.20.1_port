package com.deveisia.aoa3.content.entity.mob.overworld;

import net.minecraft.world.entity.Entity;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GhostEntity extends AoAMeleeMob {
	public GhostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.8125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GHOST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GHOST_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GHOST_HURT.get();
	}

	@Override
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity effectSource) {
		if (effect.getEffect() != MobEffects.INVISIBILITY)
			return false;

		return super.addEffect(effect, effectSource);
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level().isClientSide && getTarget() instanceof Player) {
			if (EntityUtil.isPlayerLookingAtEntity(((Player)getTarget()), this) && hasLineOfSight(getTarget()))
				EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 200).isAmbient());
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

}
