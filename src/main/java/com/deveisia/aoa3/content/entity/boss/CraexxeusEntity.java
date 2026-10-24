package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAAttributes;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.CraexxeusNukeEntity;
import com.deveisia.aoa3.content.entity.projectile.mob.CraexxeusShotEntity;
import com.deveisia.aoa3.util.DamageUtil;
import javax.annotation.Nullable;
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
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class CraexxeusEntity extends AoAFlyingRangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int chargeCooldown = 300;

	public CraexxeusEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3.9f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRAEXXEUS_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRAEXXEUS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRAEXXEUS_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_CRAEXXEUS_SHOOT.get();
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
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CraexxeusShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(1, 0.15f, 1));

		if (!level().isClientSide) {
			if (chargeCooldown > 0) {
				chargeCooldown--;

				if (chargeCooldown == 40)
					level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CRAEXXEUS_CHARGE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
			}
			else {
				chargeCooldown = 300;

				if (getTarget() != null) {
					LivingEntity target = getTarget();
					BaseMobProjectile projectile = new CraexxeusNukeEntity(this, BaseMobProjectile.Type.MAGIC);

					double distanceFactorX = target.getX() - projectile.getX();
					double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
					double distanceFactorZ = target.getZ() - projectile.getZ();
					double hyp = (float)Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

					level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CRAEXXEUS_NUKE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
					projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
					level().addFreshEntity(projectile);
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof CraexxeusNukeEntity) {
			DamageUtil.dealBlasterDamage(this, target, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 3, false);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			XxeusEntity xxeus = new XxeusEntity(this);

			level().addFreshEntity(xxeus);
			discard();
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
