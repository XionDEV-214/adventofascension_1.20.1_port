package com.deveisia.aoa3.content.item.weapon.cannon;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.cannon.OrangeCannonballEntity;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IroCannon extends BaseCannon {
	public IroCannon(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_BALL_CANNON_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new OrangeCannonballEntity(shooter, this, hand, 120, 100);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
