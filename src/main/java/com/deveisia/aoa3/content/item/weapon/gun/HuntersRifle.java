package com.deveisia.aoa3.content.item.weapon.gun;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;

public class HuntersRifle extends BaseGun {
	public HuntersRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_RIFLE_MEDIUM_FIRE_LONG.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}
}
