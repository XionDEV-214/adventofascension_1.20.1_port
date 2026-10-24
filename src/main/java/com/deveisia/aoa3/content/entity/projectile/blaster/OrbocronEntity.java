package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class OrbocronEntity extends BaseEnergyShot {
	public OrbocronEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public OrbocronEntity(Level world) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), world);
	}

	public OrbocronEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), shooter, weapon, maxAge);
	}

	public OrbocronEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), world, x, y, z);
	}
}
