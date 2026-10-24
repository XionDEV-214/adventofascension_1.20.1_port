package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class IonShotEntity extends BaseEnergyShot {
	public IonShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public IonShotEntity(Level world) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), world);
	}

	public IonShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), shooter, weapon, maxAge);
	}

	public IonShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), world, x, y, z);
	}
}
