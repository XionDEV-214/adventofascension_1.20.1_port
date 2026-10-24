package com.deveisia.aoa3.content.world.gen.feature.features.trees;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import com.deveisia.aoa3.content.block.functional.plant.SaplingBlock;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import org.apache.commons.lang3.tuple.Triple;

public abstract class AoATreeFeature extends Feature<BlockStateConfiguration> {
	protected final Supplier<SaplingBlock> sapling;

	public AoATreeFeature(Codec<BlockStateConfiguration> codec, Supplier<SaplingBlock> sapling) {
		super(codec);

		this.sapling = sapling;
	}

	public boolean generate(ServerLevel world, RandomSource rand, BlockPos pos) {
		return generateTree(world, rand, pos, false);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
		WorldGenLevel reader = context.level();
		ChunkGenerator generator = context.chunkGenerator();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		BlockStateConfiguration config = context.config();

		return generateTree(reader, rand, pos, true);
	}

	protected abstract boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen);

	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state, boolean overwriteBlocks) {
		if (!overwriteBlocks && !isSafeBlock(reader, pos))
			return;

		reader.setBlock(pos, state, 19);
	}

	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state) {
		placeBlock(reader, pos, state, false);
	}

	protected static boolean isSafeBlock(WorldGenLevel reader, BlockPos pos) {
		BlockState existingState = reader.getBlockState(pos);

		return existingState.isAir() || existingState.canBeReplaced() || existingState.is(BlockTags.LEAVES);
	}

	protected boolean checkSafeHeight(WorldGenLevel reader, BlockPos pos, int maxHeight, int trunkWidth, boolean isWorldGen) {
		if (pos.getY() >= reader.getMinBuildHeight() + 1 && pos.getY() + maxHeight < reader.getMaxBuildHeight()) {
			if (isWorldGen)
				return true;

			for (int i = 1; i <= maxHeight; i++) {
				for (int x = 0; x < trunkWidth; x++) {
					for (int z = 0; z < trunkWidth; z++) {
						if (!isSafeBlock(reader, pos.offset(x, i, z)))
							return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	protected boolean checkAndPrepSoil(WorldGenLevel reader, BlockPos pos, int trunkWidth, boolean isWorldGen) {
		if (isWorldGen)
			return true;

		ArrayList<Triple<BlockState, Block, BlockPos>> soils = new ArrayList<Triple<BlockState, Block, BlockPos>>(trunkWidth * trunkWidth);

		for (int x = 0; x < trunkWidth; x++) {
			for (int z = 0; z < trunkWidth; z++) {
				BlockPos soilPos = pos.offset(x, -1, z);
				BlockState soilState = reader.getBlockState(soilPos);
				Block soil = soilState.getBlock();

				if (soilState.isAir() || !soilState.canSustainPlant(reader, soilPos, Direction.UP, sapling.get()))
					return false;

				soils.add(Triple.of(soilState, soil, soilPos));
			}
		}

		for (Triple<BlockState, Block, BlockPos> soil : soils) {
			reader.setBlock(soil.getRight().above(), Blocks.AIR.defaultBlockState(), 2);
		}

		return true;
	}

	@Nullable
	protected BlockPos findMultiSaplingPosition(WorldGenLevel reader, RandomSource rand, BlockPos pos, int size, boolean isWorldGen) {
		if (isWorldGen)
			return pos;

		for (int x = 0; x >= -(size - 1); --x) {
			shuffleSearch:
			for (int z = 0; z >= -(size - 1); --z) {
				BlockPos checkPos = pos;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (reader.getBlockState(checkPos = pos.offset(x + i, 0, z + j)).getBlock() != sapling.get())
							continue shuffleSearch;
					}
				}

				return checkPos.offset(-size + 1, 0, -size + 1);
			}
		}

		return null;
	}
}
