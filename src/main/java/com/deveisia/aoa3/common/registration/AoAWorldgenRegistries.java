package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.world.biomemodifier.AoASpawnsBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoAWorldgenRegistries {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Codec<AoASpawnsBiomeModifier>> AOA_SPAWNS_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("entity_spawns", () -> AoASpawnsBiomeModifier.CODEC);
}
