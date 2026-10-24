package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MoonDestroyerShotEntity extends BaseEnergyShot {
	public MoonDestroyerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MoonDestroyerShotEntity(Level world) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), world);
	}

	public MoonDestroyerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), shooter, weapon, maxAge);
	}

	public MoonDestroyerShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.15f;
	}
}
