package com.deveisia.aoa3.content.item.weapon.sniper;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.gun.MoonMakerEntity;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class MoonMaker extends BaseSniper {
	public MoonMaker(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_RIFLE_MEDIUM_FIRE_LONG.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_2;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new MoonMakerEntity(shooter, this, 0);
	}
}
