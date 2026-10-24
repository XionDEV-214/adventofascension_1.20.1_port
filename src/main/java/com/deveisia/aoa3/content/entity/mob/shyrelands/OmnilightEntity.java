package com.deveisia.aoa3.content.entity.mob.shyrelands;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.OmnilightShotEntity;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class OmnilightEntity extends AoAFlyingRangedMob {
    public OmnilightEntity(EntityType<? extends FlyingMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.5f;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_OMNILIGHT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_OMNILIGHT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_OMNILIGHT_HURT.get();
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
       return new OmnilightShotEntity(this, BaseMobProjectile.Type.MAGIC);
    }

    @Override
    public int getAttackSwingDuration() {
        return 25;
    }

    @Override
    public int getPreAttackTime() {
        return 13;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
        animationData.add(AoAAnimations.genericFlyController(this));
        animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SHOOT));
    }
}
