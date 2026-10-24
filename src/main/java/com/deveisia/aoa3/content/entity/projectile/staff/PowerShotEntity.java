package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class PowerShotEntity extends BaseEnergyShot {
	public PowerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public PowerShotEntity(Level world) {
		super(AoAEntities.Projectiles.POWER_SHOT.get(), world);
	}

	public PowerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.POWER_SHOT.get(), shooter, weapon, maxAge);
	}

	public PowerShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.POWER_SHOT.get(), world, x, y, z);
	}
}
