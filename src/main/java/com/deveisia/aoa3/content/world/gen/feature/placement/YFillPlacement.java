package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class YFillPlacement extends PlacementModifier {
	public static final Codec<YFillPlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("bottom_offset", 0).forGetter(placement -> placement.bottomOffset),
			Codec.INT.optionalFieldOf("top_offset", 0).forGetter(placement -> placement.topOffset),
			Codec.INT.optionalFieldOf("maximum", 0).forGetter(placement -> placement.maximum))
			.apply(builder, YFillPlacement::new));

	private final int bottomOffset;
	private final int topOffset;
	private final int maximum;

	public YFillPlacement(int bottomOffset, int topOffset, int maximum) {
		this.bottomOffset = bottomOffset;
		this.topOffset = topOffset;
		this.maximum = maximum;
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		return IntStream.range(bottomOffset, maximum + 1).mapToObj(y -> new BlockPos(pos.getX(), y, pos.getZ()));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.Y_FILL.get();
	}
}
