package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class BloodDrainerEntity extends BaseEnergyShot {
	public BloodDrainerEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BloodDrainerEntity(Level world) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), world);
	}

	public BloodDrainerEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), shooter, weapon, maxAge);
	}

	public BloodDrainerEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), world, x, y, z);
	}
}
