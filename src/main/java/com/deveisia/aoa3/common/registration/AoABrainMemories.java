package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoABrainMemories {
	public static final DeferredRegister<MemoryModuleType<?>> MEMORIES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<MemoryModuleType<List<Projectile>>> INCOMING_PROJECTILES = registerMemoryType("incoming_projectiles");

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id, Codec<T> codec) {
		return MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.of(codec)));
	}

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id) {
		return MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.empty()));
	}
}
