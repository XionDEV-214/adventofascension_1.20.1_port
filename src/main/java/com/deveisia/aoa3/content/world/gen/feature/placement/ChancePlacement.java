package com.deveisia.aoa3.content.world.gen.feature.placement;

import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class ChancePlacement extends PlacementFilter {
	public static final Codec<ChancePlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.FLOAT.fieldOf("probability").forGetter(placement -> placement.probability))
			.apply(builder, ChancePlacement::new));

	private final float probability;

	public ChancePlacement(float probability) {
		this.probability = probability;
	}

	@Override
	protected boolean shouldPlace(PlacementContext context, RandomSource rand, BlockPos pos) {
		return rand.nextFloat() < probability;
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAFeatures.Placements.CHANCE.get();
	}
}
