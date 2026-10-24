package com.deveisia.aoa3.content.entity.mob.deeplands;

import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class DwellerEntity extends AoAMeleeMob {
    public DwellerEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 2.125f;
    }

}
