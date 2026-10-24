package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.base.AoAFlyingRangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.CottonCandorShotEntity;
import com.deveisia.aoa3.content.entity.projectile.staff.*;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CottonCandorEntity extends AoAFlyingRangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Byte> STAGE = SynchedEntityData.<Byte>defineId(CottonCandorEntity.class, EntityDataSerializers.BYTE);
	private int stageCountdown = 100;

	public CottonCandorEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.71875f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(STAGE, (byte)0);
	}

	private void changeStage(int stage) {
		entityData.set(STAGE, (byte)(stage & 0x7));
	}

	public int getStage() {
		return (int)entityData.get(STAGE);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_COTTON_CANDOR_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void actuallyHurt(DamageSource source, float amount) {
		float mod = 0.25f;

		if (source.getDirectEntity() instanceof Projectile) {
			Projectile projectile = (Projectile)source.getDirectEntity();

			switch (getStage()) {
				case 0:
					if (projectile instanceof PrimordialShotEntity)
						mod = 1.25f;
					break;
				case 1:
					if (projectile instanceof WaterShotEntity)
						mod = 1.25f;
					break;
				case 2:
					if (projectile instanceof FirestormFallEntity || projectile instanceof FireflyShotEntity || projectile instanceof BaronShotEntity)
						mod = 1.25f;
					break;
				case 3:
					if (projectile instanceof PoisonShotEntity || projectile instanceof NoxiousShotEntity)
						mod = 1.25f;
					break;
				case 4:
					if (projectile instanceof WitherShotEntity)
						mod = 1.25f;
					break;
				case 5:
					if (projectile instanceof LunarFallEntity)
						mod = 1.25f;
					break;
				default:
					break;
			}
		}

		super.actuallyHurt(source, amount * mod);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CottonCandorShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide) {
			stageCountdown--;

			if (stageCountdown == 0) {
				changeStage(random.nextInt(5));
				stageCountdown = 100;
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level(), projectile, 5f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level(), projectile, 5f);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);

				if (cause.getDirectEntity() instanceof PrimordialShotEntity && (killer.getMainHandItem().getItem() == AoAWeapons.WIND_STAFF.get() || killer.getOffhandItem().getItem() == AoAWeapons.WIND_STAFF.get()))
					AdvancementUtil.completeAdvancement((ServerPlayer)killer, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "candyland/when_push_comes_to_shove"), "wind_staff_cotton_candor_kill");
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
