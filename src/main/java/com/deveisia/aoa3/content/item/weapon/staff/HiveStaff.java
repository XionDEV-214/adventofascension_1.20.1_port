package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HiveStaff extends BaseStaff<Object> {
	public HiveStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SHADOW_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 10);
		runes.put(AoAItems.LIFE_RUNE.get(), 2);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {

	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
