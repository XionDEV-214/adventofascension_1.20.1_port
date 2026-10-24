package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class DoomShotEntity extends BaseEnergyShot {
	public DoomShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public DoomShotEntity(Level world) {
		super(AoAEntities.Projectiles.DOOM_SHOT.get(), world);
	}

	public DoomShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.DOOM_SHOT.get(), shooter, weapon, maxAge);
	}

	public DoomShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DOOM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.03f;
	}
}
