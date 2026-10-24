package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CrystalGreatblade extends BaseGreatblade {
	public CrystalGreatblade() {
		super(22.0f, AttackSpeed.GREATBLADE, 1480);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		for (LivingEntity entity : target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2.0f), EntityUtil.Predicates.HOSTILE_MOB)) {
			DamageUtil.dealAoeDamage(null, attacker, entity, RandomUtil.RANDOM.source().nextFloat() * 1.5f * attackCooldown, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
