package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class WeightedShowerShotEntity extends BaseEnergyShot {
	public WeightedShowerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public WeightedShowerShotEntity(Level world) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), world);
	}

	public WeightedShowerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WeightedShowerShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
