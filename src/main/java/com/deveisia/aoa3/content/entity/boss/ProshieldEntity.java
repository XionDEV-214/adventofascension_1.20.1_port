package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.DamageUtil;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class ProshieldEntity extends AoAMeleeMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public ProshieldEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_IMMORTAL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_IMMORTAL_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ANVIL_LAND;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (DamageUtil.isMeleeDamage(source))
			source.getDirectEntity().hurt(damageSources().mobAttack(this), amount / 2);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable Component name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}

}
