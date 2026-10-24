package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class ConstructShotEntity extends BaseMobProjectile {
	public ConstructShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ConstructShotEntity(Level world) {
		super(AoAEntities.Projectiles.CONSTRUCT_SHOT.get(), world);
	}

	public ConstructShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CONSTRUCT_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
