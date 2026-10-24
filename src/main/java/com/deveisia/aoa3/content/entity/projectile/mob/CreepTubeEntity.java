package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class CreepTubeEntity extends BaseMobProjectile {
	public CreepTubeEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CreepTubeEntity(Level world) {
		super(AoAEntities.Projectiles.CREEP_TUBE.get(), world);
	}

	public CreepTubeEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEP_TUBE.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && tickCount % 4 == 0)
			WorldUtil.createExplosion(getOwner(), level(), this, 2f);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
