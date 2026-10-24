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

public class FloroRPGEntity extends BaseBullet implements HardProjectile {
	private LivingEntity shooter;

	public FloroRPGEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public FloroRPGEntity(Level world) {
		super(AoAEntities.Projectiles.FLORO_RPG.get(), world);
	}

	public FloroRPGEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.FLORO_RPG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public FloroRPGEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FLORO_RPG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level(), this, 3.0f);
	}
}
