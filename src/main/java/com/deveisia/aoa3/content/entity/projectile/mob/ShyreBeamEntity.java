package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class ShyreBeamEntity extends BaseMobProjectile {
	public ShyreBeamEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ShyreBeamEntity(Level world) {
		super(AoAEntities.Projectiles.SHYRE_BEAM.get(), world);
	}

	public ShyreBeamEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SHYRE_BEAM.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
