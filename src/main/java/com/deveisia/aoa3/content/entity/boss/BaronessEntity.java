package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import com.deveisia.aoa3.content.entity.misc.BaronBombEntity;
import com.deveisia.aoa3.content.entity.projectile.mob.BaronessShotEntity;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BaronessEntity extends AoARangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.<Boolean>defineId(BaronessEntity.class, EntityDataSerializers.BOOLEAN);
	private int invulnerableTicks = 0;
	private int bombCoolown = 150;

	public BaronessEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.71875f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(INVULNERABLE, false);
	}

	@Override
	public boolean isInvulnerable() {
		return this.entityData.get(INVULNERABLE);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ARIEL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ARIEL_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ARIEL_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_BARONESS_SHOOT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new BaronessShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		invulnerableTicks = 40;
		changeStage(true);
	}

	private void changeStage(boolean invulnerable) {
		this.entityData.set(INVULNERABLE, invulnerable);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!isAlive())
			return;

		if (!level().isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}

		if (isNoAi())
			return;

		if (invulnerableTicks > 0) {
			invulnerableTicks--;

			if (invulnerableTicks == 0)
				changeStage(false);
		}

		if (bombCoolown > 0) {
			bombCoolown--;

			if (bombCoolown == 0) {
				bombCoolown = 50 + random.nextInt(50);
				LivingEntity target = getTarget();

				if (target != null)
					push(Math.signum((target.getX() - getX()) * 2.329), Math.signum((target.getY() + 1 - getY()) * 0.929), Math.signum(target.getZ() - getZ()) * 2.329);

				if (!level().isClientSide) {
					BaronBombEntity bomb = new BaronBombEntity(this);

					level().addFreshEntity(bomb);
					level().playSound(null, getX(), getY(), getZ(), AoASounds.BARON_BOMB_SPAWN.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
				}
			}
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource fallSource) {
		return false;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.BARONESS.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);
		}
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.BARONESS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.BARONESS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
