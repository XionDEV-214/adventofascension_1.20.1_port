package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.VoxxulonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class VoxxulonMeteorEntity extends BaseMobProjectile {
	public VoxxulonMeteorEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public VoxxulonMeteorEntity(Level world) {
		super(AoAEntities.Projectiles.VOXXULON_METEOR.get(), world);
	}

	public VoxxulonMeteorEntity(VoxxulonEntity shooter, Entity target, Type projectileType) {
		super(AoAEntities.Projectiles.VOXXULON_METEOR.get(), shooter.level(), shooter, target, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
