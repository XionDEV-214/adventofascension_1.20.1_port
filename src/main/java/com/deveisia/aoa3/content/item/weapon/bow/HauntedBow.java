package com.deveisia.aoa3.content.item.weapon.bow;

import com.deveisia.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HauntedBow extends BaseBow {
	public HauntedBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		arrow.setIgnoreExplosions();

		return arrow;
	}

	@Override
	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {
		if (!arrow.level().isClientSide && !arrow.isArrowInGround() && arrow.tickCount % 2 == 0)
			WorldUtil.createExplosion(shooter, arrow.level(), arrow, 1.0f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
