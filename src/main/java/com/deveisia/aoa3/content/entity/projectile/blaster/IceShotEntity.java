package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class IceShotEntity extends BaseEnergyShot {
	public IceShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public IceShotEntity(Level world) {
		super(AoAEntities.Projectiles.ICE_SHOT.get(), world);
	}

	public IceShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ICE_SHOT.get(), shooter, weapon, maxAge);
	}

	public IceShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ICE_SHOT.get(), world, x, y, z);
	}
}
