package com.deveisia.aoa3.content.item.weapon.thrown;

import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.thrown.GrenadeEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Grenade extends BaseThrownWeapon {
	public Grenade() {
		super(0.0f, 10);
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.5f;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new GrenadeEntity(shooter, this);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
