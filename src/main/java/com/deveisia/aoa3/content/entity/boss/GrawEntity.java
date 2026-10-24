package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.GrawShotEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GrawEntity extends AoAFlyingRangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int pullCountdown = 400;

	public GrawEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GRAW_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GRAW_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GRAW_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
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

		if (!level().isClientSide) {
			if (getY() > 110)
				setDeltaMovement(getDeltaMovement().add(-1.5 + random.nextInt(3), -4.5, -1.5 + random.nextInt(3)));

			pullCountdown--;

			if (pullCountdown < 60) {
				if (pullCountdown <= 0)
					pullCountdown = 400;

				for (Player pl : level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(85), PlayerUtil::shouldPlayerBeAffected)) {
					if (!pl.isShiftKeyDown()) {
						pl.push(Math.signum(getX() - pl.getX()) * 0.139, Math.signum(getY() - pl.getY()) * 0.04, Math.signum(getZ() - pl.getZ()) * 0.139);
						pl.hurtMarked = true;
					}
				}
			}
			else {
				for (Player pl : level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(85), PlayerUtil::shouldPlayerBeAffected)) {
					if (!pl.isShiftKeyDown()) {
						pl.push(Math.signum(getX() - pl.getX()) * 0.008, Math.signum(getY() - pl.getY()) * 0.005, Math.signum(getZ() - pl.getZ()) * 0.008);
						pl.hurtMarked = true;
					}
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level(), projectile, 2.5f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level(), projectile, 2.5f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new GrawShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.GRAW.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.GRAW_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.GRAW_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
