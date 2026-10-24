package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MoonlightFallEntity extends BaseEnergyShot {
	public MoonlightFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MoonlightFallEntity(Level world) {
		super(AoAEntities.Projectiles.MOONLIGHT_FALL.get(), world);
	}

	public MoonlightFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public MoonlightFallEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), world, x, y, z);
	}
}
