package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class GoofyGreatblade extends BaseGreatblade {
	public GoofyGreatblade() {
		super(22.0f, AttackSpeed.GREATBLADE, 1300);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		return super.getDamageForAttack(target, attacker, swordStack, baseDamage + (float)(RandomUtil.RANDOM.source().nextGaussian() * 5f));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.BENEFICIAL, Component.literal(String.valueOf(getDamage() - 5)), Component.literal(String.valueOf(getDamage() + 5))));
	}
}
