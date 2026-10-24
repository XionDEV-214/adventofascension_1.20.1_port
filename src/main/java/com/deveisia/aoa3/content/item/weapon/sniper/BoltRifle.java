package com.deveisia.aoa3.content.item.weapon.sniper;

import com.deveisia.aoa3.common.registration.AoASounds;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;

public class BoltRifle extends BaseSniper {
	public BoltRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_METALLIC_FIRE_LONG.get();
	}
}
