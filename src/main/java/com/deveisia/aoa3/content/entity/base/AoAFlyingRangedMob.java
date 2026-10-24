package com.deveisia.aoa3.content.entity.base;

import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.deveisia.aoa3.common.registration.AoAAttributes;
import com.deveisia.aoa3.content.entity.ai.animation.Animatable;
import com.deveisia.aoa3.content.entity.ai.mob.FlyingLookRandomlyGoal;
import com.deveisia.aoa3.content.entity.ai.mob.FlyingRangedAttackGoal;
import com.deveisia.aoa3.content.entity.ai.mob.RandomFlyingGoal;
import com.deveisia.aoa3.content.entity.ai.movehelper.RoamingFlightMovementController;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AoAFlyingRangedMob extends FlyingMob implements Enemy, RangedAttackMob, AoARangedAttacker, Animatable {
	protected boolean isSlipperyMovement = false;

	private final AnimatableInstanceCache animationFactory = GeckoLibUtil.createInstanceCache(this);

	protected AoAFlyingRangedMob(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);

		moveControl = new RoamingFlightMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
		goalSelector.addGoal(2, new FlyingLookRandomlyGoal(this));
		goalSelector.addGoal(3, new FlyingRangedAttackGoal(this, Math.max(getAttackSwingDuration() + 1, 40), 80));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, 10, true, true, pl -> pl instanceof Player && PlayerUtil.shouldPlayerBeAffected((Player)pl)));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		xpReward = (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 2) / 10f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn);

	@Override
	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	public int getAttackSwingDuration() {
		return 6;
	}

	public int getPreAttackTime() {
		return 0;
	}

	@Nullable
	protected abstract SoundEvent getShootSound();

	protected void onHit(DamageSource source, float amount) {}

	@Override
	public void aiStep() {
		this.updateSwingTime();
		super.aiStep();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			onHit(source, amount);

			return true;
		}

		return false;
	}

	@Override
	public void performRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
		BaseMobProjectile projectile = getNewProjectileInstance();

		double distanceFactorX = target.getX() - projectile.getX();
		double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
		double distanceFactorZ = target.getZ() - projectile.getZ();
		double hyp = (float)Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		if (getShootSound() != null)
			level().playSound(null, getX(), getY(), getZ(), getShootSound(), SoundSource.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - level().getDifficulty().getId()));
		level().addFreshEntity(projectile);
	}

	protected abstract BaseMobProjectile getNewProjectileInstance();

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		boolean success;

		switch (projectile.getProjectileType()) {
			case MAGIC:
				success = DamageUtil.dealMagicDamage(projectile, this, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()), false);
				break;
			case GUN:
				success = DamageUtil.dealGunDamage(target, this, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				break;
			case PHYSICAL:
				success = DamageUtil.dealRangedDamage(target, this, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				break;
			case OTHER:
			default:
				success = target.hurt(damageSources().mobProjectile(projectile, this), (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				break;
		}

		if (success)
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.NONE;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}

	public int getCurrentSwingDuration() {
		int time = getAttackSwingDuration();

		if (MobEffectUtil.hasDigSpeed(this))
			time -= 1 + MobEffectUtil.getDigSpeedAmplification(this);

		if (hasEffect(MobEffects.DIG_SLOWDOWN))
			time += (1 + getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier()) * 2;

		return time;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.animationFactory;
	}
}
