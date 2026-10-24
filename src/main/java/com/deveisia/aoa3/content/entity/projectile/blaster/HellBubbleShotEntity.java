package com.deveisia.aoa3.content.entity.projectile.blaster;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.item.EnergyProjectileWeapon;
import com.deveisia.aoa3.util.EntityUtil;
import java.util.List;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class HellBubbleShotEntity extends BaseEnergyShot {
	public HellBubbleShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HellBubbleShotEntity(Level world) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), world);
	}

	public HellBubbleShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), shooter, weapon, maxAge);
	}

	public HellBubbleShotEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.3d, 0.3d));

		if (getAge() >= 100)
			discard();

		if (!isAlive()) {
			level().playSound(null, getX(), getY(), getZ(), AoASounds.BUBBLE_SHOT_POP.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
		}
		else if (!level().isClientSide && weapon != null) {
			List<LivingEntity> collidingEntities = level().getEntitiesOfClass(LivingEntity.class, getBoundingBox(), EntityUtil.Predicates.HOSTILE_MOB);

			if (!collidingEntities.isEmpty()) {
				Entity shooter = getOwner();

				if (shooter instanceof LivingEntity)
					weapon.doEntityImpact(this, collidingEntities.get(0), (LivingEntity)shooter);

				discard();
			}
		}
	}
}
