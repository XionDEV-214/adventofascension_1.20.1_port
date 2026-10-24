package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class LelyetianShotEntity extends BaseMobProjectile {
	public LelyetianShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LelyetianShotEntity(Level world) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), world);
	}

	public LelyetianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
