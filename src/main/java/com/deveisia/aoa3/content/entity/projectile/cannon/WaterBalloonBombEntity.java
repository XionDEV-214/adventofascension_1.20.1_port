package com.deveisia.aoa3.content.entity.projectile.cannon;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAGameRules;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class WaterBalloonBombEntity extends BaseBullet implements HardProjectile {
	LivingEntity shooter;

	public WaterBalloonBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public WaterBalloonBombEntity(Level world) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), world);
	}

	public WaterBalloonBombEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public WaterBalloonBombEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}

	@Override
	public void doImpactEffect() {
		Entity shooter = getOwner();
		WorldUtil.createExplosion(shooter, level(), this, 1.5f);

		if (!level().isClientSide && AoAGameRules.checkDestructiveWeaponPhysics(level()) && level().isEmptyBlock(blockPosition()) && !level().dimensionType().ultraWarm()) {
			if (!WorldUtil.canPlaceBlock(level(), blockPosition(), shooter instanceof Player ? shooter : null, null))
				return;

			int i = 1;

			while (level().getBlockState(blockPosition().below(i)).canBeReplaced() && blockPosition().getY() - i >= 0) {
				i++;
			}

			if (blockPosition().getY() - i <= 0)
				return;

			level().setBlockAndUpdate(blockPosition().below(i - 1), Blocks.WATER.defaultBlockState());
		}
	}
}
