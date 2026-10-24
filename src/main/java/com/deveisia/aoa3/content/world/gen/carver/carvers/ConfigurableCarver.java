package com.deveisia.aoa3.content.world.gen.carver.carvers;

import com.deveisia.aoa3.content.world.gen.carver.config.ConfigurableCarverConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.function.Function;

public class ConfigurableCarver extends CaveWorldCarver {
	@SuppressWarnings("unchecked")
	public ConfigurableCarver(Codec<ConfigurableCarverConfig> codec) {
		super((Codec<CaveCarverConfiguration>)(Codec<?>)codec);
	}

	@Override
	protected boolean carveBlock(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeGetter, CarvingMask carvingMask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos checkPos, Aquifer aquifer, MutableBoolean reachedSurface) {
		BlockState state = chunk.getBlockState(pos);

		if (state.hasProperty(BlockStateProperties.SNOWY))
			reachedSurface.setTrue();

		if (!canReplaceBlock(config, state))
			return false;

		BlockState carveState = getCarveState(context, config, pos, aquifer);

		if (carveState == null)
			return false;

		chunk.setBlockState(pos, carveState, false);

		if (aquifer.shouldScheduleFluidUpdate() && !carveState.getFluidState().isEmpty())
			chunk.markPosForPostprocessing(pos);

		if (reachedSurface.isTrue()) {
			checkPos.setWithOffset(pos, Direction.DOWN);

			BlockState belowState = chunk.getBlockState(checkPos);

			if (belowState.is(BlockTags.DIRT) && !belowState.hasProperty(BlockStateProperties.SNOWY)) {
				context.topMaterial(biomeGetter, chunk, checkPos, !carveState.getFluidState().isEmpty()).ifPresent(topState -> {
					chunk.setBlockState(checkPos, topState, false);

					if (!topState.getFluidState().isEmpty())
						chunk.markPosForPostprocessing(checkPos);
				});
			}
		}

		return true;
	}

	private BlockState getCarveState(CarvingContext context, CaveCarverConfiguration config, BlockPos pos, Aquifer aquifer) {
		if (pos.getY() <= config.lavaLevel.resolveY(context))
			return config instanceof ConfigurableCarverConfig configurableConfig ? configurableConfig.deepFillerBlock : Blocks.LAVA.defaultBlockState();

		return aquifer.computeSubstance(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()), 0);
	}
}
