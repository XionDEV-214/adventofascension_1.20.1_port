package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedAttacker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class CyanShotEntity extends BaseMobProjectile {
	public CyanShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CyanShotEntity(Level world) {
		super(AoAEntities.Projectiles.CYAN_SHOT.get(), world);
	}

	public CyanShotEntity(AoARangedAttacker shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CYAN_SHOT.get(), ((Entity)shooter).level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
