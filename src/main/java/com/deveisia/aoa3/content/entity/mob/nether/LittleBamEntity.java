package com.deveisia.aoa3.content.entity.mob.nether;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.KingBambambamEntity;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class LittleBamEntity extends AoAMeleeMob {
	private boolean isMinion = false;

	public LittleBamEntity(KingBambambamEntity kingBambambam) {
		this(AoAEntities.Mobs.LITTLE_BAM.get(), kingBambambam.level());

		moveTo(kingBambambam.getX(), kingBambambam.getY(), kingBambambam.getZ(), random.nextFloat() * 360, 0);

		isMinion = true;
	}

	public LittleBamEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (target instanceof KingBambambamEntity)
			return;

		super.setTarget(target);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.78125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return isMinion	? null : super.getDefaultLootTable();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("IsKingBambambamMinion"))
			isMinion = true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (isMinion)
			compound.putBoolean("IsKingBambambamMinion", true);
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, level(), 2f);
	}
}
