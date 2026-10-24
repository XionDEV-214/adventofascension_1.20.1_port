package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AllXPlacement extends PlacementModifier {
	public static final AllXPlacement INSTANCE = new AllXPlacement();
	public static final Codec<AllXPlacement> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int baseX = pos.getX() & ~15;

		return IntStream.range(0, 16).mapToObj(x -> new BlockPos(baseX + x, pos.getY(), pos.getZ()));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.ALL_X.get();
	}
}
