package com.deveisia.aoa3.content.entity.mob.shyrelands;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AxiolightEntity extends AoAMeleeMob {
    public AxiolightEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.85f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_AXIOLIGHT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_AXIOLIGHT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_AXIOLIGHT_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        List<Player> playerList = level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(12), PlayerUtil::shouldPlayerBeAffected);

        if (playerList.isEmpty()) {
            EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 20).hideParticles());
        }
        else {
            MobEffectInstance invisibility = getEffect(MobEffects.INVISIBILITY);

            if (invisibility != null && (invisibility.getDuration() == MobEffectInstance.INFINITE_DURATION || invisibility.getDuration() <= 20))
                removeEffect(MobEffects.INVISIBILITY);
        }
    }
}
