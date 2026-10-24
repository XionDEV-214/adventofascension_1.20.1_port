package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.loottable.modifier.AddItemsLootModifier;
import com.deveisia.aoa3.content.loottable.modifier.FertilisedFarmlandLootModifier;
import com.deveisia.aoa3.content.loottable.modifier.HavenLootModifier;
import com.deveisia.aoa3.content.loottable.modifier.LootModifyingItemLootModifier;
import com.deveisia.aoa3.content.loottable.modifier.PlayerEventListenerLootModifier;
import com.deveisia.aoa3.content.loottable.modifier.RollExtraTablesLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoALootModifiers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Codec<RollExtraTablesLootModifier>> ROLL_EXTRA_TABLES = LOOT_MODIFIERS.register("roll_extra_tables", () -> RollExtraTablesLootModifier.CODEC);
	public static final RegistryObject<Codec<AddItemsLootModifier>> ADD_ITEMS = LOOT_MODIFIERS.register("add_items", () -> AddItemsLootModifier.CODEC);
	public static final RegistryObject<Codec<LootModifyingItemLootModifier>> LOOT_MODIFYING_ITEM = LOOT_MODIFIERS.register("loot_modifying_items", () -> LootModifyingItemLootModifier.CODEC);
	public static final RegistryObject<Codec<PlayerEventListenerLootModifier>> PLAYER_EVENT_LISTENER = LOOT_MODIFIERS.register("player_event_listener", () -> PlayerEventListenerLootModifier.CODEC);
	public static final RegistryObject<Codec<HavenLootModifier>> HAVEN = LOOT_MODIFIERS.register("haven", () -> HavenLootModifier.CODEC);
	public static final RegistryObject<Codec<FertilisedFarmlandLootModifier>> FERTILISED_FARMLAND = LOOT_MODIFIERS.register("fertilised_farmland", () -> FertilisedFarmlandLootModifier.CODEC);
}
