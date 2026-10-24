package com.deveisia.aoa3.content.entity.projectile.gun;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class LightIronShotEntity extends BaseBullet implements HardProjectile {
	public LightIronShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LightIronShotEntity(Level world) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), world);
	}

	public LightIronShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public LightIronShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
