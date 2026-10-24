package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class BlueGuardianShotEntity extends BaseMobProjectile {
	public BlueGuardianShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BlueGuardianShotEntity(Level world) {
		super(AoAEntities.Projectiles.BLUE_GUARDIAN_SHOT.get(), world);
	}

	public BlueGuardianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.BLUE_GUARDIAN_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
