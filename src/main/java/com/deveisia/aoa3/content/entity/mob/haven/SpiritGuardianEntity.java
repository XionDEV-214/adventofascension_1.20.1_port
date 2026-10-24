package com.deveisia.aoa3.content.entity.mob.haven;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SpiritGuardianEntity extends AoAMeleeMob {
	public SpiritGuardianEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SPIRIT_GUARDIAN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SPIRIT_GUARDIAN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SPIRIT_GUARDIAN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}
}
