package com.deveisia.aoa3.content.item.weapon.bow;

import com.deveisia.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RunicBow extends BaseBow {
	public RunicBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (target != null && arrow.isCritArrow() && shooter instanceof LivingEntity) {
			for (LivingEntity entity : arrow.level().getEntitiesOfClass(LivingEntity.class, arrow.getBoundingBox().inflate(3), EntityUtil.Predicates.HOSTILE_MOB)) {
				DamageUtil.dealMagicDamage(null, (LivingEntity)shooter, entity, 2, false);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
