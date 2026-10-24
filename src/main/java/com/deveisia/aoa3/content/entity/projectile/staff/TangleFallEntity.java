package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class TangleFallEntity extends BaseEnergyShot {
	public TangleFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public TangleFallEntity(Level world) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), world);
	}

	public TangleFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public TangleFallEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), world, x, y, z);
	}
}
