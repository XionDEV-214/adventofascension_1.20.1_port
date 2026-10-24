package com.deveisia.aoa3.content.entity.projectile.cannon;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAGameRules;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class RockFragmentEntity extends BaseBullet implements HardProjectile {
	public RockFragmentEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public RockFragmentEntity(Level world) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), world);
	}

	public RockFragmentEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RockFragmentEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!level().isClientSide && AoAGameRules.checkDestructiveWeaponPhysics(level()) && level().isEmptyBlock(blockPosition())) {
			int i = 1;

			while (level().getBlockState(blockPosition().below(i)).canBeReplaced() && blockPosition().getY() - i >= 0) {
				i++;
			}

			if (blockPosition().getY() - i <= 0)
				return;

			level().setBlockAndUpdate(blockPosition().below(i - 1), Blocks.COBBLESTONE.defaultBlockState());
		}
	}

	@Override
	protected float getGravity() {
		return 0.06f;
	}
}
