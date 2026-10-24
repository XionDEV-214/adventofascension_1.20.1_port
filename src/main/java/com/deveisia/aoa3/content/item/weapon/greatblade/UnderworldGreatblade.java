package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class UnderworldGreatblade extends BaseGreatblade {
	public UnderworldGreatblade() {
		super(18.5f, AttackSpeed.GREATBLADE, 1050);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (target.getMobType() == MobType.UNDEAD)
			DamageUtil.dealMeleeDamage(attacker, target, 5 * attackCooldown, false);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
