package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RunicGreatblade extends BaseGreatblade {
	public RunicGreatblade() {
		super(24.5f, AttackSpeed.GREATBLADE, 1800);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		return super.getDamageForAttack(target, attacker, swordStack, baseDamage * 0.15f);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!target.level().isClientSide)
			DamageUtil.dealMagicDamage(null, attacker, target, getDamage() * attackCooldown * 0.85f + 1, false);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
