package com.deveisia.aoa3.content.entity.mob.nether;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InfernalEntity extends AoAMeleeMob {
    public InfernalEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.71875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_INFERNAL_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity)
            DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 10.5f, 0.5f, 10.5f);
    }

    @Override
    protected void onHit(DamageSource source, float amount) {
        if (DamageUtil.isMeleeDamage(source) && (!(source.getEntity() instanceof Player) || PlayerUtil.shouldPlayerBeAffected((Player)source.getEntity())))
            source.getEntity().setSecondsOnFire(5);
    }
}
