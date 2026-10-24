package com.deveisia.aoa3.content.world.gen.structure.processors;

import com.deveisia.aoa3.common.registration.worldgen.AoAStructureProcessors;
import com.deveisia.aoa3.content.block.functional.misc.TrophyBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;

public class TrophyProcessor extends StructureProcessor {
	public static final Codec<TrophyProcessor> CODEC = RecordCodecBuilder.create(builder ->
			builder.group(
					BlockState.CODEC.fieldOf("trophy").forGetter(instance -> instance.trophyBlock),
					BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity").forGetter(instance -> instance.entityType)
			).apply(builder, TrophyProcessor::new));

	private final BlockState trophyBlock;
	private final EntityType<?> entityType;

	public TrophyProcessor(BlockState trophyBlock, EntityType<?> entityType) {
		this.trophyBlock = trophyBlock;
		this.entityType = entityType;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return AoAStructureProcessors.TROPHY_PROCESSOR.get();
	}

	@Nullable
	@Override
	public StructureTemplate.StructureBlockInfo processBlock(LevelReader world, BlockPos templatePos, BlockPos structureCenter, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativisedBlockInfo, StructurePlaceSettings placementSettings) {
		if (blockInfo.state().getBlock() == trophyBlock.getBlock())
			return new StructureTemplate.StructureBlockInfo(relativisedBlockInfo.pos(), relativisedBlockInfo.state(), TrophyBlock.getTagForEntity(entityType).getCompound("BlockEntityTag"));

		return relativisedBlockInfo;
	}
}
