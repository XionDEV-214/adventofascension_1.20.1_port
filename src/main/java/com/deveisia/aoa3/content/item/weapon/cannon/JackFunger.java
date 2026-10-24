package com.deveisia.aoa3.content.item.weapon.cannon;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.cannon.FungalRockFragmentEntity;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class JackFunger extends BaseCannon {
	double dmg;
	int firingDelay;

	public JackFunger(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_JACK_ROCKER_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return Blocks.COBBLESTONE.asItem();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new FungalRockFragmentEntity(shooter, this, hand, 120, 0);
	}
}
