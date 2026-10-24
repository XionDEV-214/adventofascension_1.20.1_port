package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class BaseAxe extends AxeItem {
	public BaseAxe(Tier stats) {
		this(stats, new Properties().durability(stats.getUses()));
	}

	public BaseAxe(Tier stats, Item.Properties properties) {
		super(stats, 1.5F, -3F, properties);
	}
}
