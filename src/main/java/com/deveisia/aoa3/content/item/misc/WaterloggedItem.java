package com.deveisia.aoa3.content.item.misc;

import java.util.function.Supplier;
import net.minecraft.world.item.Item;

public class WaterloggedItem extends Item {
	private final Supplier<Item> decloggedItem;

	public WaterloggedItem(Supplier<Item> decloggedItem, Properties properties) {
		super(properties);

		this.decloggedItem = decloggedItem;
	}

	public Item getDecloggedItem() {
		return this.decloggedItem.get();
	}
}
