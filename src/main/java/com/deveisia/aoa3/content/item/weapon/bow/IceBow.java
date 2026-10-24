package com.deveisia.aoa3.content.item.weapon.bow;

import com.deveisia.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IceBow extends BaseBow {
	public IceBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (target instanceof LivingEntity) {
			MobEffectInstance slowEffect = ((LivingEntity)target).getEffect(MobEffects.MOVEMENT_SLOWDOWN);
			int amp = 0;

			if (slowEffect != null)
				amp = Math.min(slowEffect.getAmplifier(), 2);

			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40).level(amp + 1));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
