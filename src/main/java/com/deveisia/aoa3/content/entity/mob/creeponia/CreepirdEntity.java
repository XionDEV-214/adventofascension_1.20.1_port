package com.deveisia.aoa3.content.entity.mob.creeponia;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAFlyingMeleeMob;
import com.deveisia.aoa3.util.*;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CreepirdEntity extends AoAFlyingMeleeMob {
	public CreepirdEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.46875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CREEPIRD_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CREEPIRD_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CREEPIRD_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, level(), 2.5f);
		discard();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (this.dead && !level().isClientSide()) {
			Player player = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (player != null && player.hasEffect(MobEffects.POISON) && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.MYSTERIUM_REALMSTONE.get()));
		}
	}
}
