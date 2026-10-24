package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ConfettiShotEntity extends BaseEnergyShot {
	public ConfettiShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ConfettiShotEntity(Level world) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), world);
	}

	public ConfettiShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), shooter, weapon, maxAge);
	}

	public ConfettiShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), world, x, y, z);
	}

	@Override
	protected void onHit(HitResult result) {
		if (!level().isClientSide)
			discard();
	}
}
