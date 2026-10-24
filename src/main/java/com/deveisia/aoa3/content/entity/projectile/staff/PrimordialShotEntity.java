package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class PrimordialShotEntity extends BaseEnergyShot {
	public PrimordialShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public PrimordialShotEntity(Level world) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world);
	}

	public PrimordialShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), shooter, weapon, maxAge);
	}

	public PrimordialShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world, x, y, z);
	}
}
