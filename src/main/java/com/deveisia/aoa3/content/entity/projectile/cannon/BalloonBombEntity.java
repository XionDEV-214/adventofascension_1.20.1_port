package com.deveisia.aoa3.content.entity.projectile.cannon;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class BalloonBombEntity extends BaseBullet implements HardProjectile {
	LivingEntity shooter;

	public BalloonBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BalloonBombEntity(Level world) {
		super(AoAEntities.Projectiles.BALLOON_BOMB.get(), world);
	}

	public BalloonBombEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.BALLOON_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public BalloonBombEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BALLOON_BOMB.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level(), this, 1.5f);
	}
}
