package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class SoulStormEntity extends BaseEnergyShot {
	public SoulStormEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SoulStormEntity(Level world) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), world);
	}

	public SoulStormEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), shooter, weapon, maxAge);
	}

	public SoulStormEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SOUL_STORM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.6f;
	}
}
