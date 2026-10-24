package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AoAProfessions {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<VillagerProfession> WANDERER = PROFESSIONS.register("wanderer", () -> new VillagerProfession("wanderer", holder -> false, holder -> false, ImmutableSet.of(), ImmutableSet.of(), null));
}
