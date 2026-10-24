package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.Item;

public class RuneSource extends Item {
	private final int runeGenFactor;

	public RuneSource(int runeGenFactor) {
		super(AoAItemGroups.MISC_ITEMS.properties());

		this.runeGenFactor = runeGenFactor;
	}

	public int getRuneGenFactor() {
		return this.runeGenFactor;
	}
}
