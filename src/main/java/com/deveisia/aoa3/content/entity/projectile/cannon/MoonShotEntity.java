package com.deveisia.aoa3.content.entity.projectile.cannon;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MoonShotEntity extends BaseBullet implements HardProjectile {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public MoonShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MoonShotEntity(Level world) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), world);
	}

	public MoonShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public MoonShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), world, x, y, z);
	}
}
