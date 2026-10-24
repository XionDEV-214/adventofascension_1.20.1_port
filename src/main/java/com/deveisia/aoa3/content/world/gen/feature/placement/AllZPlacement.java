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

public class AllZPlacement extends PlacementModifier {
	public static final AllZPlacement INSTANCE = new AllZPlacement();
	public static final Codec<AllZPlacement> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int baseZ = pos.getZ() & ~15;

		return IntStream.range(0, 16).mapToObj(z -> new BlockPos(pos.getX(), pos.getY(), baseZ + z));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.ALL_Z.get();
	}
}
