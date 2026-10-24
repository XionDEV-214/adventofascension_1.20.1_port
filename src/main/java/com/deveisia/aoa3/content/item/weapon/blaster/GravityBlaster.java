package com.deveisia.aoa3.content.item.weapon.blaster;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class GravityBlaster extends BaseBlaster {
	public GravityBlaster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GRAVITY_BLASTER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		for (LivingEntity mob : shooter.level().getEntitiesOfClass(LivingEntity.class, shooter.getBoundingBox().inflate(2, 0, 2), EntityUtil.Predicates.HOSTILE_MOB)) {
			EntityUtil.pushEntityAway(shooter, mob, 0.5f);
			mob.hurt(AoADamageTypes.source(mob.level(), AoADamageTypes.BLASTER), (float)getDamage());
		}

		shooter.hurtMarked = true;
		shooter.push(0, 2f, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
