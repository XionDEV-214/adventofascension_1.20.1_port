package com.deveisia.aoa3.content.item.weapon.gun;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RockerRifle extends BaseGun {
	public RockerRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_HEAVY_GENERIC_FIRE_2.get();
	}

	@Override
	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		return shooter.isShiftKeyDown() ? 0.25f : super.getRecoilForShot(stack, shooter);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
