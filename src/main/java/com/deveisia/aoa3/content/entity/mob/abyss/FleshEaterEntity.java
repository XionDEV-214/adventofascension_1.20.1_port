package com.deveisia.aoa3.content.entity.mob.abyss;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class FleshEaterEntity extends AoAMeleeMob {
	public FleshEaterEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_FLESH_EATER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_FLESH_EATER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_FLESH_EATER_HURT.get();
	}

	@Override
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity effectSource) {
		return false;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.HUNGER, 160).level(16));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
}
