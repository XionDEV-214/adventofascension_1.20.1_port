package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MindBlasterShotEntity extends BaseEnergyShot {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public MindBlasterShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MindBlasterShotEntity(Level world) {
		super(AoAEntities.Projectiles.MIND_BLASTER_SHOT.get(), world);
	}

	public MindBlasterShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.MIND_BLASTER_SHOT.get(), shooter, weapon, maxAge);
	}

	public MindBlasterShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MIND_BLASTER_SHOT.get(), world, x, y, z);
	}
}
