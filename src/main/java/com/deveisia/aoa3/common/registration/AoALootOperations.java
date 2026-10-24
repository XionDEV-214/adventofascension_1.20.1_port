package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.loottable.condition.BlockHasTag;
import com.deveisia.aoa3.content.loottable.condition.HoldingItem;
import com.deveisia.aoa3.content.loottable.condition.PlayerHasLevel;
import com.deveisia.aoa3.content.loottable.condition.PlayerHasResource;
import com.deveisia.aoa3.content.loottable.function.EnchantSpecific;
import com.deveisia.aoa3.content.loottable.function.GrantSkillXp;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AoALootOperations {
	public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, AdventOfAscension.MOD_ID);
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, AdventOfAscension.MOD_ID);

	public static final class LootFunctions {
		public static void init() {}

		public static final RegistryObject<LootItemFunctionType> ENCHANT_SPECIFIC = LOOT_FUNCTIONS.register("enchant_specific", () -> new LootItemFunctionType(new EnchantSpecific.Serializer()));
		public static final RegistryObject<LootItemFunctionType> GRANT_SKILL_XP = LOOT_FUNCTIONS.register("grant_skill_xp", () -> new LootItemFunctionType(new GrantSkillXp.Serializer()));
	}

	public static final class LootConditions {
		public static void init() {}

		public static final RegistryObject<LootItemConditionType> HOLDING_ITEM = LOOT_CONDITIONS.register("holding_item", () -> new LootItemConditionType(new HoldingItem.Serializer()));
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_LEVEL = LOOT_CONDITIONS.register("player_has_level", () -> new LootItemConditionType(new PlayerHasLevel.Serializer()));
		public static final RegistryObject<LootItemConditionType> PLAYER_HAS_RESOURCE = LOOT_CONDITIONS.register("player_has_resource", () -> new LootItemConditionType(new PlayerHasResource.Serializer()));
		public static final RegistryObject<LootItemConditionType> HAS_BLOCK_TAG = LOOT_CONDITIONS.register("has_block_tag", () -> new LootItemConditionType(new BlockHasTag.Serializer()));
	}

	public static void init() {
		LootFunctions.init();
		LootConditions.init();
	}
}
