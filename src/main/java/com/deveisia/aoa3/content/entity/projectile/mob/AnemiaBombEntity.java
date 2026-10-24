package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class AnemiaBombEntity extends BaseMobProjectile {
	public AnemiaBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AnemiaBombEntity(Level world) {
		super(AoAEntities.Projectiles.ANEMIA_BOMB.get(), world);
	}

	public AnemiaBombEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.ANEMIA_BOMB.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
