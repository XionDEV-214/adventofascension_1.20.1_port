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

public class DischargeSlugEntity extends BaseBullet implements HardProjectile {
	public DischargeSlugEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public DischargeSlugEntity(Level world) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), world);
	}

	public DischargeSlugEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public DischargeSlugEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public DischargeSlugEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level(), this, 1.8f);
	}
}
