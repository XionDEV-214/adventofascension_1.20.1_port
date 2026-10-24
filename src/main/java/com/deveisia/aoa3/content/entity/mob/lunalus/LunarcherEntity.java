package com.deveisia.aoa3.content.entity.mob.lunalus;

import com.deveisia.aoa3.common.registration.AoAAttributes;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import com.deveisia.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.item.weapon.bow.BaseBow;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class LunarcherEntity extends AoAFlyingRangedMob {
	public LunarcherEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		SpawnGroupData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAWeapons.LUNAR_BOW.get()));
		setDropChance(EquipmentSlot.MAINHAND, 0);

		return data;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_LUNARCHER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_LUNARCHER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_LUNARCHER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundEvents.ARROW_SHOOT;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	public void performRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
		CustomArrowEntity projectile = new CustomArrowEntity(level(), (BaseBow)AoAWeapons.LUNAR_BOW.get(), this, getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));

		double distanceFactorX = target.getX() - projectile.getX();
		double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
		double distanceFactorZ = target.getZ() - projectile.getZ();
		double hyp = (float)Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		if (getShootSound() != null)
			playSound(getShootSound(), 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
		level().addFreshEntity(projectile);
	}
}
