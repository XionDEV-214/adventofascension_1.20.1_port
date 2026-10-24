package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class CraexxeusShotEntity extends BaseMobProjectile {
	public CraexxeusShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CraexxeusShotEntity(Level world) {
		super(AoAEntities.Projectiles.CRAEXXEUS_SHOT.get(), world);
	}

	public CraexxeusShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CRAEXXEUS_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
