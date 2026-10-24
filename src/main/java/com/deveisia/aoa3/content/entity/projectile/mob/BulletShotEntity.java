package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class BulletShotEntity extends BaseMobProjectile {
	public BulletShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BulletShotEntity(Level world) {
		super(AoAEntities.Projectiles.BULLET_SHOT.get(), world);
	}

	public BulletShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.BULLET_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
