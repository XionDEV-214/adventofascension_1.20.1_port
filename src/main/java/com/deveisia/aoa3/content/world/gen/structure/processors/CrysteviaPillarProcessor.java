package com.deveisia.aoa3.content.world.gen.structure.processors;

import com.deveisia.aoa3.common.registration.worldgen.AoAStructureProcessors;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;

public class CrysteviaPillarProcessor extends StructureProcessor {
	public static final Codec<CrysteviaPillarProcessor> CODEC = RecordCodecBuilder.create(builder ->
			builder.group(
					BlockState.CODEC.fieldOf("pillar_block").forGetter(instance -> instance.pillarBlock)
			).apply(builder, CrysteviaPillarProcessor::new));

	private final BlockState pillarBlock;

	public CrysteviaPillarProcessor(BlockState pillarBlock) {
		this.pillarBlock = pillarBlock;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return AoAStructureProcessors.CRYSTEVIA_PILLAR.get();
	}

	@Nullable
	@Override
	public StructureTemplate.StructureBlockInfo processBlock(LevelReader world, BlockPos pos1, BlockPos pos2, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativisedBlockInfo, StructurePlaceSettings placementSettings) {
		if (blockInfo.state().is(Blocks.BEDROCK)) {
			BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos().set(relativisedBlockInfo.pos());
			BlockState testBlock = world.getBlockState(placementPos);

			world.getChunk(placementPos).setBlockState(placementPos, pillarBlock, false);

			while (testBlock.isAir() && placementPos.getY() >= world.getMinBuildHeight()) {
				world.getChunk(placementPos).setBlockState(placementPos, pillarBlock, false);
				placementPos.move(Direction.DOWN);
				testBlock = world.getBlockState(placementPos);
			}

			return new StructureTemplate.StructureBlockInfo(relativisedBlockInfo.pos(), pillarBlock, null);
		}

		return relativisedBlockInfo;
	}
}
