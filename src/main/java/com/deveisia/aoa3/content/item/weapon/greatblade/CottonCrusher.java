package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CottonCrusher extends BaseGreatblade {
	public CottonCrusher() {
		super(24.0f, AttackSpeed.GREATBLADE, 1600);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attacker instanceof Player && attackCooldown > 0.95f)
			((Player)attacker).getFoodData().eat(1, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
