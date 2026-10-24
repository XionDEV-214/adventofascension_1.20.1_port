package com.deveisia.aoa3.content.entity.projectile.gun;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class SeedDartEntity extends BaseBullet implements HardProjectile {
	public SeedDartEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SeedDartEntity(Level world) {
		super(AoAEntities.Projectiles.SEED_DART.get(), world);
	}

	public SeedDartEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SEED_DART.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SeedDartEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SEED_DART.get(), world, x, y, z);
	}
}
