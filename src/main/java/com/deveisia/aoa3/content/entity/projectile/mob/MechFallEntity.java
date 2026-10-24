package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.MechbotEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MechFallEntity extends BaseMobProjectile {
	public MechFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MechFallEntity(Level world) {
		super(AoAEntities.Projectiles.MECH_FALL.get(), world);
	}

	public MechFallEntity(MechbotEntity shooter, double posX, double posY, double posZ, Type projectileType) {
		super(AoAEntities.Projectiles.MECH_FALL.get(), shooter.level(), shooter, posX, posY, posZ, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
