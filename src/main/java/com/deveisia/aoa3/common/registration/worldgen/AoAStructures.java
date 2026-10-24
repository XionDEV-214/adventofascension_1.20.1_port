package com.deveisia.aoa3.common.registration.worldgen;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.world.gen.structure.AoAStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AoAStructures {
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, AdventOfAscension.MOD_ID);

	public static final RegistryObject<StructureType<AoAStructure>> AOA_STRUCTURE = STRUCTURE_TYPES.register("aoa_structure", () -> () -> AoAStructure.CODEC);
}
