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

public class ShoeShotEntity extends BaseBullet implements HardProjectile {
	public ShoeShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ShoeShotEntity(Level world) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), world);
	}

	public ShoeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ShoeShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.13f;
	}

	public void doImpactEffect(Entity target) {}
}
