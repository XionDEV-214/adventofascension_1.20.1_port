package com.deveisia.aoa3.content.entity.mob.nowhere;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.ItemUtil;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GoldusEntity extends AoAMeleeMob {
	public GoldusEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.5f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level().isClientSide) {
			Entity attacker = cause.getEntity();

			if (attacker instanceof Player || attacker instanceof TamableAnimal) {
				Player pl = null;

				if (attacker instanceof TamableAnimal) {
					if (((TamableAnimal)attacker).getOwner() instanceof Player)
						pl = (Player)((TamableAnimal)attacker).getOwner();
				}
				else {
					pl = (Player)attacker;
				}

				if (pl != null && !pl.getInventory().contains(new ItemStack(AoAItems.PURE_GOLD.get()))) {
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.PURE_GOLD.get()));
					pl.inventoryMenu.broadcastChanges();
				}
			}
		}
	}
}
