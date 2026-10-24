package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class DestructionShotEntity extends BaseEnergyShot {
	public DestructionShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public DestructionShotEntity(Level world) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), world);
	}

	public DestructionShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), shooter, weapon, maxAge);
	}

	public DestructionShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DESTRUCTION_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.4d, 0.4d, 0.4d));

		if (getAge() >= 65) {
			WorldUtil.createExplosion(getOwner(), level(), this, 3.2f);
			discard();
		}
	}
}
