package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class NightmareFallEntity extends BaseEnergyShot {
	public NightmareFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public NightmareFallEntity(Level world) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), world);
	}

	public NightmareFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public NightmareFallEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), world, x, y, z);
	}
}
