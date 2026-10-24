package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.mob.creeponia.CreeperlockEntity;
import com.deveisia.aoa3.content.entity.mob.creeponia.MagicalCreeperEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class CreeperShotEntity extends BaseMobProjectile {
	public CreeperShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CreeperShotEntity(Level world) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), world);
	}

	public CreeperShotEntity(CreeperlockEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	public CreeperShotEntity(MagicalCreeperEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
