package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class VineWizardShotEntity extends BaseMobProjectile {
	public VineWizardShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public VineWizardShotEntity(Level world) {
		super(AoAEntities.Projectiles.VINE_WIZARD_SHOT.get(), world);
	}

	public VineWizardShotEntity(AoARangedMob shooter, Entity target, Type projectileType) {
		super(AoAEntities.Projectiles.VINE_WIZARD_SHOT.get(), shooter.level(), shooter, target, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
