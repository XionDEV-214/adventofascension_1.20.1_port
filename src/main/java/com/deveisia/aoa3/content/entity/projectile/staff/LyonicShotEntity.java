package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class LyonicShotEntity extends BaseEnergyShot {
	public LyonicShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LyonicShotEntity(Level world) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), world);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public LyonicShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), world, x, y, z);
	}
}
