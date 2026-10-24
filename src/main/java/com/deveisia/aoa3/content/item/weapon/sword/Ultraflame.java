package com.deveisia.aoa3.content.item.weapon.sword;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Ultraflame extends BaseSword {
	public Ultraflame() {
		super(ItemUtil.customItemTier(2250, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.75) {
			for (LivingEntity entity : target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3), EntityUtil.Predicates.HOSTILE_MOB)) {
				entity.setSecondsOnFire(3);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
