package com.deveisia.aoa3.integration.jei.ingredient.subtype;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class TrophySubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
	public static final TrophySubtypeInterpreter INSTANCE = new TrophySubtypeInterpreter();

	@Override
	public String apply(ItemStack stack, UidContext context) {
		CompoundTag tag = stack.getTag();

		if (tag == null || !tag.contains("BlockEntityTag"))
			return NONE;

		CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

		if (!blockEntityTag.contains("EntityID"))
			return NONE;

		return blockEntityTag.getString("EntityID");
	}
}
