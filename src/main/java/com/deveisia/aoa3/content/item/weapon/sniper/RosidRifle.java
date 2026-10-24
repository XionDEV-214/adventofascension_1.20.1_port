package com.deveisia.aoa3.content.item.weapon.sniper;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RosidRifle extends BaseSniper {
	public RosidRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_HEAVY_FIRE_LONG.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_2;
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {

	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
