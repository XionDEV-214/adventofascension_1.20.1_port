package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.recipe.InfusionRecipe;
import com.deveisia.aoa3.content.recipe.ToolInteractionRecipe;
import com.deveisia.aoa3.content.recipe.TrophyRecipe;
import com.deveisia.aoa3.content.recipe.UpgradeKitRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class AoARecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AdventOfAscension.MOD_ID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, AdventOfAscension.MOD_ID);

	public static final RecipeEntry<UpgradeKitRecipe> UPGRADE_KIT = registerRecipeType("upgrade_kit", UpgradeKitRecipe.Factory::new);
	public static final RecipeEntry<InfusionRecipe> INFUSION = registerRecipeType("infusion", InfusionRecipe.Factory::new);
	public static final RecipeEntry<TrophyRecipe> TROPHY = registerRecipeType("trophy", TrophyRecipe.Factory::new);
	public static final RecipeEntry<ToolInteractionRecipe> TOOL_INTERACTION = registerRecipeType("tool_interaction", ToolInteractionRecipe.Factory::new);

	private static <T extends Recipe<?>> RecipeEntry<T> registerRecipeType(String id, Supplier<RecipeSerializer<T>> serializer) {
		RegistryObject<RecipeType<T>> type = RECIPE_TYPES.register(id, () -> RecipeType.simple(AdventOfAscension.id(id)));

		return new RecipeEntry<T>(type, RECIPES.register(id, serializer));
	}

	public static final class RecipeEntry<T extends Recipe<?>> {
		private final RegistryObject<RecipeType<T>> type;
		private final RegistryObject<RecipeSerializer<T>> serializer;

		private RecipeEntry(RegistryObject<RecipeType<T>> type, RegistryObject<RecipeSerializer<T>> serializer) {
			this.type = type;
			this.serializer = serializer;
		}

		public RecipeType<T> getA() {
			return this.type.get();
		}

		public RegistryObject<RecipeSerializer<T>> getB() {
			return this.serializer;
		}

		public RecipeType<T> type() {
			return this.type.get();
		}

		public RecipeSerializer<T> serializer() {
			return this.serializer.get();
		}
	}
}
