package com.deveisia.aoa3.content.entity.projectile.gun;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MoonMakerEntity extends BaseBullet implements HardProjectile {
	public MoonMakerEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MoonMakerEntity(Level world) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), world);
	}

	public MoonMakerEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public MoonMakerEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), world, x, y, z);
	}
}
