package com.deveisia.aoa3.content.world.biomemodifier;

import com.deveisia.aoa3.common.registration.AoAEntitySpawns;
import com.deveisia.aoa3.common.registration.AoAWorldgenRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public final class AoASpawnsBiomeModifier implements BiomeModifier {
	public static final AoASpawnsBiomeModifier INSTANCE = new AoASpawnsBiomeModifier();
	public static final Codec<AoASpawnsBiomeModifier> CODEC = Codec.unit(INSTANCE);

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD)
			AoAEntitySpawns.applySpawns(biome, builder.getMobSpawnSettings());
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return AoAWorldgenRegistries.AOA_SPAWNS_MODIFIER.get();
	}
}
