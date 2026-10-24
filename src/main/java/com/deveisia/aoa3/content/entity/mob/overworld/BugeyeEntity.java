package com.deveisia.aoa3.content.entity.mob.overworld;

import software.bernie.geckolib.core.animation.AnimatableManager;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.ScreenOverlayPacket;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BugeyeEntity extends AoAMeleeMob {
	public BugeyeEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.65f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_BUGEYE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_BUGEYE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_BUGEYE_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target.isAlive() && target instanceof ServerPlayer)
			AoAPackets.messagePlayer((ServerPlayer)target, new ScreenOverlayPacket(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/circles.png"), 150));
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 18;
	}

	@Override
	protected int getPreAttackTime() {
		return 14;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericWalkIdleController(this));
		animationData.add(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
