package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class LightSparkEntity extends BaseEnergyShot {
	public LightSparkEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LightSparkEntity(Level world) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), world);
	}

	public LightSparkEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), shooter, weapon, maxAge);
	}

	public LightSparkEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.1d, 0.1d, 0.1d));
	}
}
