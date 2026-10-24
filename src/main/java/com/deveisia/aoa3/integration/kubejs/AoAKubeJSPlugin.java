package com.deveisia.aoa3.integration.kubejs;

import com.deveisia.aoa3.advent.AdventOfAscension;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeNamespace;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;

public class AoAKubeJSPlugin extends KubeJSPlugin {
	private static final RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
	private static final RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
	private static final RecipeKey<InputItem> UPGRADE_KIT = ItemComponents.INPUT.key("upgrade_kit");
	private static final RecipeKey<InputItem[]> INGREDIENTS = ItemComponents.UNWRAPPED_INPUT_ARRAY.key("ingredients");
	private static final RecipeKey<Integer> INFUSION_LEVEL = NumberComponent.INT.key("infusion_level").optional(1);
	private static final RecipeKey<String> GROUP = StringComponent.ANY.key("group").optional("").exclude();
	private static final RecipeKey<OutputItem> OPTIONAL_RESULT = ItemComponents.OUTPUT.key("result").defaultOptional();
	private static final RecipeKey<InputItem> OPTIONAL_INPUT = ItemComponents.INPUT.key("input").defaultOptional();
	private static final RecipeKey<InputItem> TOOL = ItemComponents.INPUT.key("tool");

	public static final RecipeSchema UPGRADE_KIT_SCHEMA = new RecipeSchema(RESULT, INPUT, UPGRADE_KIT, GROUP);
	public static final RecipeSchema INFUSION_SCHEMA = new RecipeSchema(INGREDIENTS, OPTIONAL_RESULT, OPTIONAL_INPUT, INFUSION_LEVEL, GROUP);
	public static final RecipeSchema TOOL_INTERACTION_SCHEMA = new RecipeSchema(RESULT, INGREDIENTS, TOOL);

	@Override
	public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
		RecipeNamespace namespace = event.namespace(AdventOfAscension.MOD_ID);

		namespace.register("upgrade_kit", UPGRADE_KIT_SCHEMA);
		namespace.register("infusion", INFUSION_SCHEMA);
		namespace.register("tool_interaction", TOOL_INTERACTION_SCHEMA);
	}
}
