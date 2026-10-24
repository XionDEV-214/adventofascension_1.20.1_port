package com.deveisia.aoa3.content.entity.projectile.cannon;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class GigaGreenBallEntity extends BaseBullet {
	public GigaGreenBallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public GigaGreenBallEntity(Level world) {
		super(AoAEntities.Projectiles.GIGA_GREEN_BALL.get(), world);
	}

	public GigaGreenBallEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.GIGA_GREEN_BALL.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public GigaGreenBallEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GIGA_GREEN_BALL.get(), world, x, y, z);
	}
}
