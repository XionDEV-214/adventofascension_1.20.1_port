package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.levelgen.Heightmap;

public class FlashEntity extends AoAMeleeMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public FlashEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(3.2f);
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
	public boolean hurt(DamageSource source, float amount) {
		if (!level().isClientSide && DamageUtil.isMeleeDamage(source)) {
			if (WorldUtil.isWorld(level(), AoADimensions.NOWHERE.key)) {
				switch (random.nextInt(3)) {
					case 0:
						teleportTo(235, 22, 10);
						break;
					case 1:
						teleportTo(235, 22, -3);
						break;
					case 2:
						teleportTo(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getX() + (random.nextInt(14) - 7);
				int z = (int)getZ() + (random.nextInt(14) - 7);
				setPos(x, level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlockPos.containing(x, getY(), z)).getY(), z);
			}
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void onAttack(Entity target) {
		if (!level().isClientSide) {
			if (WorldUtil.isWorld(level(), AoADimensions.NOWHERE.key)) {
				switch (random.nextInt(3)) {
					case 0:
						teleportTo(235, 22, 10);
						break;
					case 1:
						teleportTo(235, 22, -3);
						break;
					case 2:
						teleportTo(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getX() + (random.nextInt(14) - 7);
				int z = (int)getZ() + (random.nextInt(14) - 7);
				setPos(x, level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlockPos.containing(x, getY(), z)).getY(), z);
			}
		}
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
