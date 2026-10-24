package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class HeightmapSpreadDoublePlacement extends PlacementModifier {
	public static final HeightmapSpreadDoublePlacement INSTANCE = new HeightmapSpreadDoublePlacement();
	public static final Codec<HeightmapSpreadDoublePlacement> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource rand, BlockPos pos) {
		int minY = context.getMinGenY();
		int height = context.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ()) - minY;

		if (height <= 0)
			return Stream.empty();

		return Stream.of(new BlockPos(pos.getX(), minY + rand.nextInt(height * 2), pos.getZ()));
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.HEIGHTMAP_SPREAD_DOUBLE.get();
	}
}
