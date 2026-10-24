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

public class OrangeCannonballEntity extends BaseBullet implements HardProjectile {
	public OrangeCannonballEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public OrangeCannonballEntity(Level world) {
		super(AoAEntities.Projectiles.ORANGE_CANNONBALL.get(), world);
	}

	public OrangeCannonballEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.ORANGE_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public OrangeCannonballEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ORANGE_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
