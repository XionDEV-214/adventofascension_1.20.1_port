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

public class AboveHeightmapPlacement extends PlacementModifier {
	public static final Codec<AboveHeightmapPlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("min", 0).forGetter(placement -> placement.min),
			Codec.INT.optionalFieldOf("max", 0).forGetter(placement -> placement.max))
			.apply(builder, AboveHeightmapPlacement::new));

	private final int min;
	private final int max;

	public AboveHeightmapPlacement(int min, int max) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = context.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
		int offset = min == max ? max : min + rand.nextInt(1 + max - min);

		return y > context.getMinGenY() ? Stream.of(new BlockPos(x, Math.min(y + offset, context.getMinGenY() + context.getGenDepth()), z)) : Stream.empty();
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.ABOVE_HEIGHTMAP.get();
	}
}
