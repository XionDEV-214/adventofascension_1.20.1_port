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

public class GoldenCannonballEntity extends BaseBullet implements HardProjectile {
	public GoldenCannonballEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public GoldenCannonballEntity(Level world) {
		super(AoAEntities.Projectiles.GOLDEN_CANNONBALL.get(), world);
	}

	public GoldenCannonballEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.GOLDEN_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue, xMod, yMod, zMod);
	}

	public GoldenCannonballEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GOLDEN_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
