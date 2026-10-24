package com.deveisia.aoa3.common.registration.worldgen;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.world.gen.densityfunction.AsteroidNoiseFunction;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AoADensityFunctions {
	public static final DeferredRegister<Codec<? extends DensityFunction>> DENSITY_FUNCTIONS = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Codec<AsteroidNoiseFunction>> ASTEROID_NOISE = DENSITY_FUNCTIONS.register("asteroid_noise", AsteroidNoiseFunction.CODEC::codec);
}
