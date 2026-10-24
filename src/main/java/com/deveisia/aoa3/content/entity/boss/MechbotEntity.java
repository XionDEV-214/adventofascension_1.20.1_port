package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.base.AoARangedAttacker;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.MechFallEntity;
import com.deveisia.aoa3.content.entity.projectile.mob.MechShotEntity;
import com.deveisia.aoa3.util.DamageUtil;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.block.state.BlockState;

public class MechbotEntity extends AoAMeleeMob implements AoARangedAttacker {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int jumpCooldown = 200;
	private int jumpCount = 0;
	private int jumpIntervalTimer = 0;

	public MechbotEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.21875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MECHYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MECHYON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MECHYON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (getTarget() == null || !isAlive())
			return;

		LivingEntity target = getTarget();

		if (jumpCooldown > 0)
			jumpCooldown--;

		if (jumpCooldown <= 0) {
			if (jumpCount < 4) {
				jumpIntervalTimer--;

				if (jumpIntervalTimer <= 0) {
					setDeltaMovement((target.getX() - getX()) * 0.045, 0.699, (target.getZ() - getZ()) * 0.045);

					hurtMarked = true;

					if (!level().isClientSide) {
						level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MECHBOT_JUMP.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
						level().addFreshEntity(new MechFallEntity(this, getX(), getY() - 0.25, getZ(), BaseMobProjectile.Type.MAGIC));

						if (getHealth() < 1250) {
							level().addFreshEntity(new MechFallEntity(this, getX() - 2.25, getY() - 0.25, getZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							level().addFreshEntity(new MechFallEntity(this, getX() - 2.25, getY() - 0.25, getZ() + 2.25, BaseMobProjectile.Type.MAGIC));
							level().addFreshEntity(new MechFallEntity(this, getX() + 2.25, getY() - 0.25, getZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							level().addFreshEntity(new MechFallEntity(this, getX() + 2.25, getY() - 0.25, getZ() + 2.25, BaseMobProjectile.Type.MAGIC));
						}
					}

					jumpCount++;
					jumpIntervalTimer = 15;
				}
			}
			else {
				jumpCooldown = 200;
				jumpCount = 0;
				jumpIntervalTimer = 15;
			}
		}

		if (getHealth() < 1000 ? random.nextInt(10) == 0 : random.nextInt(40) == 0) {
			MechShotEntity projectile = new MechShotEntity(this, BaseMobProjectile.Type.MAGIC);

			double distanceFactorX = target.getX() - this.getX();
			double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - projectile.getY();
			double distanceFactorZ = target.getZ() - this.getZ();
			double hyp = (float)Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

			level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MECHBOT_SHOOT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
			projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
			level().addFreshEntity(projectile);
		}

		if (!level().isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.MECHBOT.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.dealBlasterDamage(this, target, projectile, 5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level(), projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level(), projectile, 1f);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
