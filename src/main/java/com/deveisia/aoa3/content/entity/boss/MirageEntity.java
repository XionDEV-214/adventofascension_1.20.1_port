package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.SpiritualShotEntity;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

public class MirageEntity extends AoARangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public MirageEntity(EntityType<? extends Monster> entityType, Level world) {
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

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_MIRAGE_SHOOT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new SpiritualShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide) {
			if (random.nextInt(80) == 0) {
				level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MIRAGE_TELEPORT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

				if (WorldUtil.isWorld(level(), AoADimensions.NOWHERE.key)) {
					switch (random.nextInt(4)) {
						case 0:
							moveTo(167, 24, 8, random.nextFloat() * 360, 0);
							break;
						case 1:
							moveTo(168, 24, -2, random.nextFloat() * 360, 0);
							break;
						case 2:
							moveTo(177, 24, 8, random.nextFloat() * 360, 0);
							break;
						case 3:
							moveTo(177, 24, -2, random.nextFloat() * 360, 0);
							break;
					}
				}
				else {
					int x = (int)(random.nextBoolean() ? getX() + 5 : getX() - 5);
					int z = (int)(random.nextBoolean() ? getZ() + 5 : getZ() - 5);

					moveTo(x, level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlockPos.containing(x, getY(), z)).getY(), z, random.nextFloat() * 360, 0);
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
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
