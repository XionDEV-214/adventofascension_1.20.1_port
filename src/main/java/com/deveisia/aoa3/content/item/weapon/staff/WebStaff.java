package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class WebStaff extends BaseStaff<ArrayList<MobEffect>> {
	public WebStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_WEB_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 4);
		runes.put(AoAItems.ENERGY_RUNE.get(), 4);
	}

	@Override
	public ArrayList<MobEffect> checkPreconditions(LivingEntity caster, ItemStack staff) {
		ArrayList<MobEffect> effects = new ArrayList<MobEffect>(caster.getActiveEffects().size());

		for (MobEffectInstance effect : caster.getActiveEffects()) {
			if (!effect.getEffect().isBeneficial())
				effects.add(effect.getEffect());
		}

		return effects.size() > 0 ? effects : null;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, ArrayList<MobEffect> args) {
		args.forEach(caster::removeEffect);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
