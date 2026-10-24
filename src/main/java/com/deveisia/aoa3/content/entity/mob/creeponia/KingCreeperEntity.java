package com.deveisia.aoa3.content.entity.mob.creeponia;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.mob.nether.SkeletalCowmanEntity;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class KingCreeperEntity extends AoACreeponiaCreeper {
    public KingCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);

        fuseTime = 80;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.40625f;
    }

    @Override
	public float getExplosionStrength() {
        return 6f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AoASounds.ENTITY_CREEPOID_HURT.get();
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT) && cause.getEntity() instanceof SkeletalCowmanEntity)
            spawnAtLocation(AoAItems.MUSIC_DISC_OUTLAW.get(), 1);
    }
}
