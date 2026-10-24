package com.deveisia.aoa3.common.registration.worldgen;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.world.gen.structure.processors.CrysteviaPillarProcessor;
import com.deveisia.aoa3.content.world.gen.structure.processors.TrophyProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AoAStructureProcessors {
	public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, AdventOfAscension.MOD_ID);

	public static final RegistryObject<StructureProcessorType<CrysteviaPillarProcessor>> CRYSTEVIA_PILLAR = PROCESSORS.register("crystevia_pillar", () -> () -> CrysteviaPillarProcessor.CODEC);
	public static final RegistryObject<StructureProcessorType<TrophyProcessor>> TROPHY_PROCESSOR = PROCESSORS.register("trophy_processor", () -> () -> TrophyProcessor.CODEC);
}
