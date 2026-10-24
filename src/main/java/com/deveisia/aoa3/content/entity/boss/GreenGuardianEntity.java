package com.deveisia.aoa3.content.entity.boss;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.MusicPacket;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoARangedMob;
import com.deveisia.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import com.deveisia.aoa3.content.entity.projectile.mob.GreenGuardianShotEntity;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GreenGuardianEntity extends AoARangedMob {
	private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(getType().getDescription().copy().append(getDisplayName()), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private BlueGuardianEntity blueGuardian;
	private RedGuardianEntity redGuardian;
	private YellowGuardianEntity yellowGuardian;

	public GreenGuardianEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.875f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 35, 60, 32));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GUARDIAN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GUARDIAN_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_GUARDIAN_SHOOT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		if (checkGuardian(blueGuardian) && checkGuardian(redGuardian) && checkGuardian(yellowGuardian))
			return super.getDefaultLootTable();

		return null;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	public void setRedGuardian(RedGuardianEntity redGuardian) {
		this.redGuardian = redGuardian;
	}

	public void setBlueGuardian(BlueGuardianEntity blueGuardian) {
		this.blueGuardian = blueGuardian;
	}

	public void setYellowGuardian(YellowGuardianEntity yellowGuardian) {
		this.yellowGuardian = yellowGuardian;
	}

	private boolean checkGuardian(LivingEntity guardian) {
		return guardian == null || !guardian.isAlive() || guardian.getHealth() == 0;
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
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			discard();

			if (checkGuardian(yellowGuardian) && checkGuardian(blueGuardian) && checkGuardian(redGuardian)) {
				for (ServerPlayer pl : level().getEntitiesOfClass(ServerPlayer.class, getBoundingBox().inflate(20))) {
					AdvancementUtil.completeAdvancement(pl, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "haven/guard_that"), "kill_four_guardians");
				}
			}
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target instanceof AoARangedMob && !target.canChangeDimensions())
			return;

		super.doProjectileEntityImpact(projectile, target);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level(), projectile, 2f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level(), projectile, 2f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new GreenGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.GREEN_GUARDIAN_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.GREEN_GUARDIAN_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
