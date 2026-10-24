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

public class HeavyTriDischargeShotEntity extends BaseBullet implements HardProjectile {
	public HeavyTriDischargeShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HeavyTriDischargeShotEntity(Level world) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), world);
	}

	public HeavyTriDischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public HeavyTriDischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HeavyTriDischargeShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), world, x, y, z);
	}
}
