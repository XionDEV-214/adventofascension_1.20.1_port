package com.deveisia.aoa3.content.entity.mob.lborean;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.ai.mob.AnimatableMeleeAttackGoal;
import com.deveisia.aoa3.content.entity.base.AoAWaterMeleeMob;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class MuncherEntity extends AoAWaterMeleeMob {
	public MuncherEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		if (EntityUtil.isNaturalSpawnReason(reason)) {
			BlockPos.MutableBlockPos spawnPos = new BlockPos.MutableBlockPos().set(blockPosition());

			while (!world.getBlockState(spawnPos).blocksMotion() && spawnPos.getY() > 0) {
				spawnPos.move(Direction.DOWN);
			}

			setPos(spawnPos.getX(), spawnPos.getY() + 1, spawnPos.getZ());
		}

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new AnimatableMeleeAttackGoal<>(this).ignoreLineOfSight().preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.96875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MUNCHER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MUNCHER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MUNCHER_HURT.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void push(Entity entity) {}

	@Override
	public void push(double x, double y, double z) {}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!onGround())
			setDeltaMovement(getDeltaMovement().add(0, -0.005, 0));
	}

	@Override
	protected int getAttackSwingDuration() {
		return 20;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
