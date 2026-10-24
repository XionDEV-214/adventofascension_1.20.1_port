package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CrystonStaff extends BaseStaff<Integer> {
	public CrystonStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CRYSTEVIA_STAFF_CAST.get();
	}

	@Override
	public Integer checkPreconditions(LivingEntity caster, ItemStack staff) {
		int count = caster.level().getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(10), EntityUtil.Predicates.HOSTILE_MOB).size();

		if (count > 0)
			return count;

		return null;
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.ENERGY_RUNE.get(), 4);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Integer args) {
		EntityUtil.applyPotions(caster, new EffectBuilder(MobEffects.MOVEMENT_SPEED, Math.min(args * 100, 1200)).level(Math.min(args, 3)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
