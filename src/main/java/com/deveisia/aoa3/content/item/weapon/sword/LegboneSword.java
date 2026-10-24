package com.deveisia.aoa3.content.item.weapon.sword;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LegboneSword extends BaseSword {
	public LegboneSword() {
		super(ItemUtil.customItemTier(2000, AttackSpeed.NORMAL, 12.5f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.75f)
			target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2, false, true));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
