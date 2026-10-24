package com.deveisia.aoa3.content.item.weapon.gun;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LunarAssaultRifle extends BaseGun {
	private final double baseDmg;
	private final double maxDmg;

	public LunarAssaultRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);

		this.baseDmg = dmg - (dmg / 2d);
		this.maxDmg = dmg + (dmg / 2d);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_3.get();
	}

	@Override
	public double getDamage() {
		return RandomUtil.randomValueBetween(baseDmg, maxDmg);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.set(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(baseDmg), LocaleUtil.numToComponent(maxDmg)));
	}
}
