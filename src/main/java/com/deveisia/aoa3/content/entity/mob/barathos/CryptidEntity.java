package com.deveisia.aoa3.content.entity.mob.barathos;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class CryptidEntity extends AoAMeleeMob {
	public CryptidEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRYPTID_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRYPTID_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRYPTID_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (level().getBlockState(blockPosition().below()).getBlock() == AoABlocks.HELLSTONE.get() && level().getBlockState(blockPosition()).canBeReplaced())
			level().setBlockAndUpdate(blockPosition(), Blocks.FIRE.defaultBlockState());
	}
}
