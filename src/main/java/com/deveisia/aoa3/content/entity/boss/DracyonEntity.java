package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.packet.packets.ScreenOverlayPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingMeleeMob;
import com.deveisia.aoa3.content.entity.base.AoARangedAttacker;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.SpectralShotEntity;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.scheduling.async.DracyonCleanupTask;
import com.deveisia.aoa3.util.*;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DracyonEntity extends AoAFlyingMeleeMob implements AoARangedAttacker {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public DracyonEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);

		this.setSpeed(3.7f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.15625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DRACYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DRACYON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DRACYON_AMBIENT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide) {
			if (random.nextInt(200) == 0 && !level().dimensionType().ultraWarm() && level().getBlockState(blockPosition()).canBeReplaced()) {
				level().setBlockAndUpdate(blockPosition(), Blocks.WATER.defaultBlockState());
				new DracyonCleanupTask(level(), blockPosition()).schedule(5, TimeUnit.SECONDS);
			}

			if (random.nextInt(70) == 0 && getTarget() != null) {
				LivingEntity target = getTarget();
				BaseMobProjectile projectile = new SpectralShotEntity(this, BaseMobProjectile.Type.MAGIC);

				double distanceFactorX = target.getX() - projectile.getX();
				double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
				double distanceFactorZ = target.getZ() - projectile.getZ();
				double hyp = (float)Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

				level().playSound(null, blockPosition(), AoASounds.ENTITY_DRACYON_AMBIENT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
				projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
				level().addFreshEntity(projectile);
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide && !isNoAi()) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.DRACYON.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof ServerPlayer)
			AoAPackets.messagePlayer((ServerPlayer)target, new ScreenOverlayPacket(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/scratches.png"), 40));
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.dealBlasterDamage(this, target, projectile, 13.5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 45));
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.DRACYON_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.DRACYON_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
