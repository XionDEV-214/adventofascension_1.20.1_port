package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class UndergroundSurfacePlacement extends PlacementModifier {
	public static final Codec<UndergroundSurfacePlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.INT.optionalFieldOf("bottom_offset", 0).forGetter(placement -> placement.bottomOffset),
			Codec.INT.optionalFieldOf("top_offset", 0).forGetter(placement -> placement.topOffset),
			Codec.INT.optionalFieldOf("maximum", 0).forGetter(placement -> placement.maximum),
			Codec.BOOL.optionalFieldOf("on_surface", false).forGetter(placement -> placement.onSurface),
			Codec.BOOL.optionalFieldOf("on_ceiling", false).forGetter(placement -> placement.onCeiling))
			.apply(builder, UndergroundSurfacePlacement::new));

	private final int bottomOffset;
	private final int topOffset;
	private final int maximum;
	private final boolean onSurface;
	private final boolean onCeiling;

	public UndergroundSurfacePlacement(int bottomOffset, int topOffset, int maximum, boolean onSurface, boolean onCeiling) {
		this.bottomOffset = bottomOffset;
		this.topOffset = topOffset;
		this.maximum = maximum;
		this.onSurface = onSurface;
		this.onCeiling = onCeiling;
	}

	private int getRandomValue(RandomSource rand) {
		return bottomOffset + rand.nextInt(Math.max(1, maximum - bottomOffset - topOffset));
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(x, getRandomValue(rand), z);

		for (int i = 0; i < 5; i++) {
			if (isEmptyPosition(context, checkPos))
				break;

			checkPos.set(x, getRandomValue(rand), z);
		}

		if (!isEmptyPosition(context, checkPos))
			return Stream.empty();

		if (onCeiling)
			return findRoofPosition(context, checkPos, x, z);

		return findFloorPosition(context, checkPos, x, z);
	}

	private Stream<BlockPos> findRoofPosition(PlacementContext context, BlockPos.MutableBlockPos startPos, int x, int z) {
		int minY = context.getMinGenY();
		int roofPos = minY + context.getGenDepth();

		while (startPos.getY() < roofPos && isEmptyPosition(context, startPos)) {
			startPos.move(Direction.UP);
		}

		if (startPos.getY() >= roofPos)
			return Stream.empty();

		if (onSurface)
			startPos.move(Direction.DOWN);

		return Stream.of(startPos.immutable());
	}

	private Stream<BlockPos> findFloorPosition(PlacementContext context, BlockPos.MutableBlockPos startPos, int x, int z) {
		int minY = context.getMinGenY();

		while (startPos.getY() > minY && isEmptyPosition(context, startPos)) {
			startPos.move(Direction.DOWN);
		}

		if (startPos.getY() <= minY)
			return Stream.empty();

		if (onSurface)
			startPos.move(Direction.UP);

		return Stream.of(startPos.immutable());
	}

	private boolean isEmptyPosition(PlacementContext context, BlockPos pos) {
		BlockState state = context.getBlockState(pos);

		return state.isAir() || !state.getFluidState().isEmpty();
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.UNDERGROUND_SURFACE.get();
	}
}
