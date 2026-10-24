package com.deveisia.aoa3.content.entity.projectile.staff;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class HaunterShotEntity extends BaseEnergyShot {
	public HaunterShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HaunterShotEntity(Level world) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), world);
	}

	public HaunterShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), shooter, weapon, maxAge);
	}

	public HaunterShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HAUNTER_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && getAge() % 4 == 0)
			WorldUtil.createExplosion(getOwner(), level(), this, 2.2f);
	}
}
