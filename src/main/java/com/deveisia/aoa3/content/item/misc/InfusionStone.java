package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class InfusionStone extends Item {
	private final RegistryObject<Item> powerStone;
	private final int lvl;
	private final float xp;

	public InfusionStone(int lvl, float xp, RegistryObject<Item> powerStone) {
		super(AoAItemGroups.MISC_ITEMS.properties());
		this.lvl = lvl;
		this.xp = xp;
		this.powerStone = powerStone;
	}

	public Item getPowerStone() {
		return powerStone.get();
	}

	public int getLvl() {
		return lvl;
	}

	public float getXp() {
		return xp;
	}
}
