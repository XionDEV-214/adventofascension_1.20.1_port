package com.deveisia.aoa3.content.entity.mob.runandor.templars;

import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class RunicLifeformEntity extends AoAMeleeMob {
	private final RuneTemplarEntity templar;

	public RunicLifeformEntity(EntityType<? extends Monster> entityType, RuneTemplarEntity templar) {
		super(entityType, templar.level());

		this.templar = templar;
	}

	public RunicLifeformEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.templar = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ANVIL_LAND;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide) {
			if (templar != null) {
				if (templar.getHealth() > 0 && templar.getHealth() < templar.getMaxHealth())
					templar.heal(0.075f);
			}
			else {
				discard();
			}
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WEAKNESS, 150).level(3));
	}
}
