package com.deveisia.aoa3.content.item.weapon.cannon;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.cannon.HeavyShadowballEntity;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ShadowBlaster extends BaseCannon {
	public ShadowBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SHADOW_BLASTER_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new HeavyShadowballEntity(shooter, this, hand, 120, 0);
	}
}
