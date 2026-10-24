package com.deveisia.aoa3.content.item.weapon.crossbow;

import com.deveisia.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LunarCrossbow extends BaseCrossbow {
	public LunarCrossbow(double damage, int durability) {
		super(damage, durability);
	}

	@Override
	protected CustomArrowEntity createArrow(LivingEntity shooter, ItemStack crossbowStack, ItemStack ammoStack) {
		CustomArrowEntity arrow = super.createArrow(shooter, crossbowStack, ammoStack);

		arrow.setNoGravity(true);

		return arrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
