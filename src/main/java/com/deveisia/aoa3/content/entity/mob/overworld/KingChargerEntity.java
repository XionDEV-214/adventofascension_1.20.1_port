package com.deveisia.aoa3.content.entity.mob.overworld;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class KingChargerEntity extends AoAMeleeMob {
	public KingChargerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		isSlipperyMovement = true;

		setSpeed(1.5f);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.5625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CHARGER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WEAKNESS, 45).level(5));
	}

}
