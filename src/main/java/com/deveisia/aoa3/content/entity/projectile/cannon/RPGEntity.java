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

public class RPGEntity extends BaseBullet implements HardProjectile {
	private LivingEntity shooter;

	public RPGEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public RPGEntity(Level world) {
		super(AoAEntities.Projectiles.RPG.get(), world);
	}

	public RPGEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.RPG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public RPGEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.RPG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!level().isClientSide)
			WorldUtil.createExplosion(shooter, level(), this, 2.7f);
	}
}
