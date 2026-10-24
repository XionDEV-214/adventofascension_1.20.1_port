package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LelyetianGreatblade extends BaseGreatblade {
	public LelyetianGreatblade() {
		super(18.5f, AttackSpeed.GREATBLADE, 1100);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		return super.getDamageForAttack(target, attacker, swordStack, EntityUtil.isFlyingCreature(target) ? baseDamage * 1.25f : baseDamage);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
