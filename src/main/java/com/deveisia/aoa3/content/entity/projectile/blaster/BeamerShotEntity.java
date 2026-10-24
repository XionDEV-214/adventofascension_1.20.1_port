package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class BeamerShotEntity extends BaseEnergyShot {
	public BeamerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BeamerShotEntity(Level world) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), world);
	}

	public BeamerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public BeamerShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.4f;
	}
}
