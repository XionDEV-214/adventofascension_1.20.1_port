package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class ShyreShotEntity extends BaseEnergyShot {
	public ShyreShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ShyreShotEntity(Level world) {
		super(AoAEntities.Projectiles.SHYRE_SHOT.get(), world);
	}

	public ShyreShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SHYRE_SHOT.get(), shooter, weapon, maxAge);
	}

	public ShyreShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SHYRE_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.085f;
	}
}
