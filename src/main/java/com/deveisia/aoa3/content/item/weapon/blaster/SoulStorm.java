package com.deveisia.aoa3.content.item.weapon.blaster;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.blaster.SoulStormEntity;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SoulStorm extends BaseBlaster {
	public SoulStorm(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SPRAYER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level().addFreshEntity(new SoulStormEntity(shooter, this, 60));
	}
}
