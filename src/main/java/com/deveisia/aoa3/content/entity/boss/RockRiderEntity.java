package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RockRiderEntity extends AoAMeleeMob {
	private static final EntityDataAccessor<Boolean> ALTERNATE_FORM = SynchedEntityData.<Boolean>defineId(RockRiderEntity.class, EntityDataSerializers.BOOLEAN);
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private boolean alternateForm = false;
	private int formCooldown = 300;

	public RockRiderEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(0.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3.0625f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(ALTERNATE_FORM, false);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ROCK_RIDER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ROCK_RIDER_HURT.get();
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

	private void changeForm(boolean alternate) {
		if (alternate) {
			formCooldown = 300;

			if (!level().isClientSide)
				this.entityData.set(ALTERNATE_FORM, true);
		}
		else {
			formCooldown = 300;

			if (!level().isClientSide)
				this.entityData.set(ALTERNATE_FORM, false);
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (entityData.get(ALTERNATE_FORM)) {
			if (DamageUtil.isRangedDamage(source, this, 1))
				return true;
		}
		else {
			if (DamageUtil.isMeleeDamage(source))
				return true;
		}

		return super.isInvulnerableTo(source);
	}

	public boolean isAlternateForm() {
		return this.entityData.get(ALTERNATE_FORM);
	}

	@Override
	public void tick() {
		super.tick();

		if (formCooldown > 0) {
			formCooldown--;
		}
		else {
			if (alternateForm) {
				alternateForm = false;

				changeForm(false);

			}
			else {
				alternateForm = true;

				changeForm(true);

			}

			if (!level().isClientSide)
				level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_ROCK_RIDER_SWITCH.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
		}

		if (!level().isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getProgress())
				bossInfo.setProgress(healthPercent);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			if (alternateForm) {
				double resist = 1;
				AttributeInstance attrib = ((LivingEntity)target).getAttribute(Attributes.KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getValue();

				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 150).level(4));
				target.push(getDeltaMovement().x() * 5 * resist, getDeltaMovement().y() * resist, getDeltaMovement().z() * 5 * resist);
				target.hurtMarked = true;
			}
			else {
				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WEAKNESS, 150).level(4));
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.ROCK_RIDER.get().getDescriptionId() + ".kill", killer.getDisplayName()), level(), blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
