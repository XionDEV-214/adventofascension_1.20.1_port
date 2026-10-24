package com.deveisia.aoa3.content.item.weapon.vulcane;

import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ImpairmentVulcane extends BaseVulcane {
	public ImpairmentVulcane(double dmg, int durability) {
		super(dmg, durability);
	}

	@Override
	public void doAdditionalEffect(LivingEntity target, Player attacker, float damageDealt) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 100).level(3));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
