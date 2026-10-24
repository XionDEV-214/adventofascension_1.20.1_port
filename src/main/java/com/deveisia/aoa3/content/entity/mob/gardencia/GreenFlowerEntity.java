package com.deveisia.aoa3.content.entity.mob.gardencia;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.VinocorneEntity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class GreenFlowerEntity extends AoAMeleeMob {
	public GreenFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.GREEN_FLOWER.get(), vinocorne.level());

		moveTo(vinocorne.getX(), vinocorne.getY(), vinocorne.getZ(), random.nextFloat() * 360, 0);
	}

	public GreenFlowerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5f;
	}

}
