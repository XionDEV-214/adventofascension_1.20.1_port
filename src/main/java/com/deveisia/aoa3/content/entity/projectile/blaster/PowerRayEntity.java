package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class PowerRayEntity extends BaseEnergyShot {
	public PowerRayEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public PowerRayEntity(Level world) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), world);
	}

	public PowerRayEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), shooter, weapon, maxAge);
	}

	public PowerRayEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), world, x, y, z);
	}
}
