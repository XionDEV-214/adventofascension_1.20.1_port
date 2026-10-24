package com.deveisia.aoa3.content.item.tool.pickaxe;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class BasePickaxe extends PickaxeItem {
	public BasePickaxe(Tier stats) {
		this(stats, AoAItemGroups.TOOLS.properties().durability(stats.getUses()));
	}

	public BasePickaxe(Tier stats, Item.Properties properties) {
		super(stats, 1, -2.8F, properties);
	}
}
