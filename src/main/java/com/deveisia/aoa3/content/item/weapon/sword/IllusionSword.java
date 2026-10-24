package com.deveisia.aoa3.content.item.weapon.sword;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IllusionSword extends BaseSword {
	public IllusionSword() {
		super(ItemUtil.customItemTier(1900, AttackSpeed.NORMAL, 14.5f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && !EntityUtil.isImmuneToSpecialAttacks(target, attacker) && RandomUtil.percentChance(0.1f * attackCooldown)) {
			List<LivingEntity> nearbyMobs = target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(5), EntityUtil.Predicates.HOSTILE_MOB);

			if (nearbyMobs.size() > 1) {
				LivingEntity newTarget = null;

				for (LivingEntity nearbyMob : nearbyMobs) {
					if (nearbyMob != attacker && nearbyMob != target) {
						newTarget = nearbyMob;

						break;
					}
				}

				if (newTarget == null)
					return;

				target.setLastHurtByMob(newTarget);

				if (target instanceof PathfinderMob)
					((PathfinderMob)target).setTarget(newTarget);

				target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0, false, true));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
