package com.deveisia.aoa3.content.item.weapon.blaster;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.blaster.SeaocronEntity;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Seaocron extends BaseBlaster {
	public Seaocron(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SHADOW_BLASTER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level().addFreshEntity(new SeaocronEntity(shooter, this, 60));
	}

	@Override
	protected void doImpactEffect(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		for (LivingEntity e : shot.level().getEntitiesOfClass(LivingEntity.class, shot.getBoundingBox().inflate(15), EntityUtil.Predicates.HOSTILE_MOB)) {
			if (!EntityUtil.isImmuneToSpecialAttacks(e, shooter))
				EntityUtil.pullEntityIn(target, e, 0.5f, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
