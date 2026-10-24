package com.deveisia.aoa3.content.entity.mob.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.ElusiveEntity;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.WorldUtil;
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

public class ElusiveCloneEntity extends AoAMeleeMob {
	private final ElusiveEntity elusive;

	public ElusiveCloneEntity(ElusiveEntity elusive) {
		super(AoAEntities.Mobs.ELUSIVE_CLONE.get(), elusive.level());

		moveTo(elusive.getX(), elusive.getY(), elusive.getZ(), elusive.getYRot(), elusive.getXRot());
		this.elusive = elusive;
	}

	public ElusiveCloneEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.elusive = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ELUSIVE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ELUSIVE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ELUSIVE_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.BLINDNESS, 80).level(3));
		EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 40));
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && elusive == null)
			discard();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide && elusive != null)
			WorldUtil.createExplosion(elusive, level(), getX(), getY(), getZ(), 2f);
	}
}
