package com.deveisia.aoa3.content.recipe;

import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.core.RegistryAccess;
import com.deveisia.aoa3.common.registration.AoARecipes;
import com.deveisia.aoa3.content.block.functional.misc.TrophyBlock;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class TrophyRecipe implements CraftingRecipe, net.minecraftforge.common.crafting.IShapedRecipe<CraftingContainer> {
	private final ResourceLocation id;
	private final String group;
	private final Item targetTrophy;
	private final Item outputTrophy;

	public TrophyRecipe(ResourceLocation recipeId, String group, String inputTrophyId, String outputTrophyId) {
		this.id = recipeId;
		this.group = group;
		this.targetTrophy = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(inputTrophyId));
		this.outputTrophy = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(outputTrophyId));
	}

	@Override
	public CraftingBookCategory category() {
		return CraftingBookCategory.MISC;
	}

	@Override
	public boolean matches(CraftingContainer inv, Level worldIn) {
		for(int x = 0; x <= inv.getWidth() - 3; ++x) {
			for(int y = 0; y <= inv.getHeight() - 3; ++y) {
				if (checkMatch(inv))
					return true;
			}
		}

		return false;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();

		for (int i = 0; i < 9; i++) {
			ingredients.add(Ingredient.of(new ItemStack(targetTrophy)));
		}

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem() == targetTrophy)
				return TrophyBlock.cloneTrophy(inv.getItem(i), outputTrophy);
		}

		return ItemStack.EMPTY;
	}

	private boolean checkMatch(CraftingContainer inv) {
		String entityType = null;

		for(int gridX = 0; gridX < inv.getWidth(); ++gridX) {
			for(int gridY = 0; gridY < inv.getHeight(); ++gridY) {
				ItemStack slotStack = inv.getItem(gridX + gridY * inv.getWidth());

				if (slotStack.getItem() != targetTrophy || !slotStack.hasTag())
					return false;

				CompoundTag tag = slotStack.getTag();

				if (!tag.contains("BlockEntityTag"))
					return false;

				CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

				if (!blockEntityTag.contains("OriginalTrophy") || !blockEntityTag.getBoolean("OriginalTrophy"))
					return false;

				if (blockEntityTag.contains("EntityID")) {
					if (entityType == null) {
						entityType = blockEntityTag.getString("EntityID");
					}
					else if (!entityType.equals(blockEntityTag.getString("EntityID"))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 3 && height >= 3;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return new ItemStack(outputTrophy);
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.TROPHY.getB().get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeType.CRAFTING;
	}

	@Override
	public int getRecipeWidth() {
		return 3;
	}

	@Override
	public int getRecipeHeight() {
		return 3;
	}

	public static class Factory implements RecipeSerializer<TrophyRecipe> {
		@Override
		public TrophyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new TrophyRecipe(recipeId, json.has("group") ? GsonHelper.getAsString(json, "group") : "", GsonHelper.getAsString(json, "input_trophy"), GsonHelper.getAsString(json, "output_trophy"));
		}

		@Nullable
		@Override
		public TrophyRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			return new TrophyRecipe(recipeId, buffer.readUtf(), buffer.readUtf(), buffer.readUtf());
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, TrophyRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeUtf(RegistryUtil.getId(recipe.targetTrophy).toString());
			buffer.writeUtf(RegistryUtil.getId(recipe.outputTrophy).toString());
		}
	}
}
