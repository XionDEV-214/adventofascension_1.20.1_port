package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class FirestormFallEntity extends BaseEnergyShot {
	public FirestormFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public FirestormFallEntity(Level world) {
		super(AoAEntities.Projectiles.FIRESTORM_FALL.get(), world);
	}

	public FirestormFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.FIRESTORM_FALL.get(),shooter, weapon, posX, posY, posZ, velocity);
	}

	public FirestormFallEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FIRESTORM_FALL.get(),world, x, y, z);
	}
}
