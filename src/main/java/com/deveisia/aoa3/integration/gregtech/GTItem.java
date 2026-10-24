package com.deveisia.aoa3.integration.gregtech;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public final class GTItem implements Supplier<Item>, ItemLike {
	private final TagPrefix prefix;
	private final Supplier<Material> material;

	private GTItem(TagPrefix prefix, Supplier<Material> material) {
		this.prefix = prefix;
		this.material = material;
	}

	public static GTItem of(TagPrefix prefix, Supplier<Material> material) {
		return new GTItem(prefix, material);
	}

	public TagPrefix prefix() {
		return this.prefix;
	}

	public Material material() {
		return this.material.get();
	}

	public TagKey<Item> tag() {
		return ChemicalHelper.getTag(this.prefix, this.material.get());
	}

	public Ingredient ingredient() {
		return Ingredient.of(tag());
	}

	public ItemStack stack(int count) {
		ItemStack stack = ChemicalHelper.get(this.prefix, this.material.get(), count);

		return stack.isEmpty() ? new ItemStack(Items.AIR) : stack;
	}

	public boolean matches(ItemStack stack) {
		return stack.is(tag());
	}

	@Override
	public Item get() {
		return ChemicalHelper.get(this.prefix, this.material.get()).getItem();
	}

	@Override
	public Item asItem() {
		return get();
	}
}
