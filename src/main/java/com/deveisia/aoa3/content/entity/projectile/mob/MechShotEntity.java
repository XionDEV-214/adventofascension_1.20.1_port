package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.MechbotEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class MechShotEntity extends BaseMobProjectile {
	public MechShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MechShotEntity(Level world) {
		super(AoAEntities.Projectiles.MECH_SHOT.get(), world);
	}

	public MechShotEntity(MechbotEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.MECH_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
