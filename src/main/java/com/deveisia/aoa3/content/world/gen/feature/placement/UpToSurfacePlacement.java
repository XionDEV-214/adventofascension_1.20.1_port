package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class UpToSurfacePlacement extends PlacementModifier {
	public static final Codec<UpToSurfacePlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("min", 0).forGetter(placement -> placement.min),
			Codec.INT.optionalFieldOf("max", 0).forGetter(placement -> placement.max))
			.apply(builder, UpToSurfacePlacement::new));

	private final int min;
	private final int max;

	public UpToSurfacePlacement(int min, int max) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int minY = context.getMinGenY();
		int y = minY + rand.nextInt(1 + Math.max(0, context.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z) - minY));

		if (y < Math.max(minY, min) || y > max)
			return Stream.empty();

		return Stream.of(new BlockPos(x, y, z));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.UP_TO_SURFACE.get();
	}
}
