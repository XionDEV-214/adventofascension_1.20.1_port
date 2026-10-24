package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import java.util.UUID;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class FireflyShotEntity extends BaseEnergyShot {
	public final UUID lastTargetUUID;

	public FireflyShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(Level world) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, UUID lastTargetUUID, double motionX, double motionY, double motionZ) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = lastTargetUUID;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, double motionX, double motionY, double motionZ) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, maxAge);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), world, x, y, z);

		this.lastTargetUUID = null;
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
