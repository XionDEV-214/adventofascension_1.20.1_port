package com.deveisia.aoa3.content.entity.mob.shyrelands;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SoulscorneEntity extends AoAMeleeMob {
    public SoulscorneEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.59375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_SOULSCORNE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_SOULSCORNE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_SOULSCORNE_HURT.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity)target;
            ArrayList<MobEffectInstance> positiveEffects = new ArrayList<MobEffectInstance>(entity.getActiveEffects().size());

            for (MobEffectInstance effect : entity.getActiveEffects()) {
                if (effect.getEffect().isBeneficial())
                    positiveEffects.add(effect);
            }

            for (MobEffectInstance effect : positiveEffects) {
                entity.removeEffect(effect.getEffect());
            }
        }
    }
}
