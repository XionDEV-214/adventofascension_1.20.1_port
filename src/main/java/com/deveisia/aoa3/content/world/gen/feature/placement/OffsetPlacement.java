package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class OffsetPlacement extends PlacementModifier {
	public static final Codec<OffsetPlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Direction.CODEC.fieldOf("direction").forGetter(placement -> placement.direction),
			Codec.INT.fieldOf("amount").forGetter(placement -> placement.amount))
			.apply(builder, OffsetPlacement::new));

	private final Direction direction;
	private final int amount;

	public OffsetPlacement(Direction direction, int amount) {
		this.direction = direction;
		this.amount = amount;
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		return Stream.of(pos.relative(direction, amount));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.OFFSET.get();
	}
}
