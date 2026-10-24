package com.deveisia.aoa3.content.entity.projectile.gun;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class DischargeShotEntity extends BaseBullet implements HardProjectile {
	public DischargeShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public DischargeShotEntity(Level world) {
		super(AoAEntities.Projectiles.DISCHARGE_SHOT.get(), world);
	}

	public DischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public DischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public DischargeShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DISCHARGE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level(), this, 1.8f);
	}
}
