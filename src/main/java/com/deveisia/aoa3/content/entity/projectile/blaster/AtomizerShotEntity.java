package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class AtomizerShotEntity extends BaseEnergyShot {
	public AtomizerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AtomizerShotEntity(Level world) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), world);
	}

	public AtomizerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), shooter, weapon, maxAge);
	}

	public AtomizerShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
