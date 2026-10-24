package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HarkosEntity extends AoAMeleeMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public HarkosEntity(MiskelEntity miskel) {
		this(AoAEntities.Mobs.HARKOS.get(), miskel.level());

		this.moveTo(miskel.getX(), miskel.getY(), miskel.getZ(), miskel.getYRot(), miskel.getXRot());
	}

	public HarkosEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(2.1f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.009375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PRIMORDIAL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PRIMORDIAL_DEATH.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide) {
			if (getHealth() > 0)
				heal(0.5f);

			if (random.nextInt(150) == 0) {
				setDeltaMovement(new Vec3(getDeltaMovement().x(), 0.8, getDeltaMovement().z()));

				this.fallDistance = -1;
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			RaxxanEntity raxxan = new RaxxanEntity(this);

			level().addFreshEntity(raxxan);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
