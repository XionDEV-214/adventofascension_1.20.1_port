package com.deveisia.aoa3.content.world.gen.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class AsteroidNoiseFunction implements DensityFunction {
	public static final MapCodec<AsteroidNoiseFunction> DATA_CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
			NoiseHolder.CODEC.fieldOf("seed_noise").forGetter(function -> function.seedNoise),
			Codec.DOUBLE.optionalFieldOf("xz_scale", 1d).forGetter(function -> function.xzScale),
			Codec.DOUBLE.optionalFieldOf("y_scale", 1d).forGetter(function -> function.yScale),
			Codec.DOUBLE.optionalFieldOf("xz_factor", 80d).forGetter(function -> function.xzFactor),
			Codec.DOUBLE.optionalFieldOf("y_factor", 160d).forGetter(function -> function.yFactor),
			Codec.DOUBLE.optionalFieldOf("density_offset", -0.46875d).forGetter(function -> function.densityOffset),
			Codec.DOUBLE.optionalFieldOf("biome_depth", 0d).forGetter(function -> function.biomeDepth),
			Codec.DOUBLE.optionalFieldOf("biome_scale", 0d).forGetter(function -> function.biomeScale),
			Codec.INT.optionalFieldOf("cell_width", 8).forGetter(function -> function.cellWidth),
			Codec.INT.optionalFieldOf("cell_height", 8).forGetter(function -> function.cellHeight),
			Codec.INT.optionalFieldOf("height", 256).forGetter(function -> function.height),
			Codec.DOUBLE.optionalFieldOf("top_slide_target", -10d).forGetter(function -> function.topSlideTarget),
			Codec.DOUBLE.optionalFieldOf("top_slide_size", 3d).forGetter(function -> function.topSlideSize),
			Codec.DOUBLE.optionalFieldOf("top_slide_offset", 0d).forGetter(function -> function.topSlideOffset),
			Codec.DOUBLE.optionalFieldOf("bottom_slide_target", -30d).forGetter(function -> function.bottomSlideTarget)
	).apply(builder, AsteroidNoiseFunction::new));
	public static final KeyDispatchDataCodec<AsteroidNoiseFunction> CODEC = KeyDispatchDataCodec.of(DATA_CODEC);

	private final NoiseHolder seedNoise;
	private final double xzScale;
	private final double yScale;
	private final double xzFactor;
	private final double yFactor;
	private final double densityOffset;
	private final double biomeDepth;
	private final double biomeScale;
	private final int cellWidth;
	private final int cellHeight;
	private final int height;
	private final double topSlideTarget;
	private final double topSlideSize;
	private final double topSlideOffset;
	private final double bottomSlideTarget;

	@Nullable
	private final PerlinNoise lateralNoise;
	@Nullable
	private final PerlinNoise verticalNoise;

	public AsteroidNoiseFunction(NoiseHolder seedNoise, double xzScale, double yScale, double xzFactor, double yFactor, double densityOffset, double biomeDepth, double biomeScale, int cellWidth, int cellHeight, int height, double topSlideTarget, double topSlideSize, double topSlideOffset, double bottomSlideTarget) {
		this.seedNoise = seedNoise;
		this.xzScale = xzScale;
		this.yScale = yScale;
		this.xzFactor = xzFactor;
		this.yFactor = yFactor;
		this.densityOffset = densityOffset;
		this.biomeDepth = biomeDepth;
		this.biomeScale = biomeScale;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.height = height;
		this.topSlideTarget = topSlideTarget;
		this.topSlideSize = topSlideSize;
		this.topSlideOffset = topSlideOffset;
		this.bottomSlideTarget = bottomSlideTarget;

		NormalNoise noise = seedNoise.noise();

		if (noise != null) {
			long seed = Double.doubleToLongBits(noise.getValue(0.123d, 4.567d, 8.901d)) * 31L + Double.doubleToLongBits(noise.getValue(-12.34d, 5.6d, 78.9d));
			LegacyRandomSource random = new LegacyRandomSource(seed);

			this.lateralNoise = PerlinNoise.createLegacyForBlendedNoise(random, IntStream.rangeClosed(-3, 0));
			this.verticalNoise = PerlinNoise.createLegacyForBlendedNoise(random, IntStream.rangeClosed(-9, 0));
		}
		else {
			this.lateralNoise = null;
			this.verticalNoise = null;
		}
	}

	private double getCombinedNoise(double posX, double posY, double posZ) {
		double horizontalNoiseScale = 684.412d * xzScale / 10d;
		double verticalNoiseScale = 684.412d * yScale * 10d;
		double horizontalNoiseScaleModifier = horizontalNoiseScale / xzFactor * 6d;
		double verticalNoiseScaleModifier = verticalNoiseScale / yFactor;
		double vertical = 0;
		double lateral = 0;
		double stage = 0.2d;

		for (int i = 0; i < 9; i++) {
			double x = PerlinNoise.wrap(posX * horizontalNoiseScaleModifier * stage);
			double y = PerlinNoise.wrap(posY * verticalNoiseScaleModifier * stage);
			double z = PerlinNoise.wrap(posZ * horizontalNoiseScaleModifier * stage);
			double verticalModifier = verticalNoiseScale * stage;
			double lateralModifier = horizontalNoiseScale * stage;
			ImprovedNoise verticalOctave = verticalNoise.getOctaveNoise(i);

			if (verticalOctave != null)
				vertical += verticalOctave.noise(x, y, z, verticalModifier, posY * verticalModifier) / stage;

			ImprovedNoise lateralOctave = lateralNoise.getOctaveNoise(i / 3);

			if (lateralOctave != null)
				lateral += lateralOctave.noise(y, x, z, lateralModifier, posY * lateralModifier * 2) / stage;

			stage /= 1.4d;
		}

		double min = Math.min(lateral, vertical);
		double max = Math.max(lateral, vertical);

		if (max - min == 0)
			return 0;

		return (vertical / 2.5d * lateral * 2.5d - min) / (max - min);
	}

	@Override
	public double compute(FunctionContext context) {
		if (lateralNoise == null || verticalNoise == null)
			return -1;

		double noiseX = context.blockX() / (double)cellWidth;
		double noiseY = context.blockY() / (double)cellHeight;
		double noiseZ = context.blockZ() / (double)cellWidth;
		double noiseSizeY = height / (double)cellHeight;
		double value = getCombinedNoise(noiseX, noiseY, noiseZ);
		double biomeDensityOffset = (biomeDepth * 0.5d - 0.125d) * 0.265625d;
		double biomeDensityFactor = 96d / (biomeScale * 0.9d + 0.1d);
		double gradient = (densityOffset + biomeDensityOffset) * biomeDensityFactor;

		value += gradient > 0 ? gradient * 4d : gradient;

		if (topSlideSize > 0)
			value = Mth.clampedLerp(topSlideTarget, value, ((noiseSizeY - noiseY) - topSlideOffset) / topSlideSize);

		value = Mth.clampedLerp(bottomSlideTarget, value, (noiseY - 1d) / 10d);

		return value / 200d;
	}

	@Override
	public void fillArray(double[] array, ContextProvider provider) {
		provider.fillAllDirectly(array, this);
	}

	@Override
	public DensityFunction mapAll(Visitor visitor) {
		return visitor.apply(new AsteroidNoiseFunction(visitor.visitNoise(seedNoise), xzScale, yScale, xzFactor, yFactor, densityOffset, biomeDepth, biomeScale, cellWidth, cellHeight, height, topSlideTarget, topSlideSize, topSlideOffset, bottomSlideTarget));
	}

	@Override
	public double minValue() {
		return -1.0E7d;
	}

	@Override
	public double maxValue() {
		return 1.0E7d;
	}

	@Override
	public KeyDispatchDataCodec<? extends DensityFunction> codec() {
		return CODEC;
	}
}
