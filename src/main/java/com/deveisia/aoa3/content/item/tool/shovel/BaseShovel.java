package com.deveisia.aoa3.content.item.tool.shovel;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class BaseShovel extends ShovelItem {
	public BaseShovel(Tier stats) {
		this(stats, new Properties().durability(stats.getUses()));
	}

	public BaseShovel(Tier stats, Item.Properties properties) {
		super(stats, 1.5F, -3.0F, properties);
	}
}
