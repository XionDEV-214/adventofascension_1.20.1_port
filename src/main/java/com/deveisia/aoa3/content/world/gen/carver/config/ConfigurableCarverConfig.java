package com.deveisia.aoa3.content.world.gen.carver.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;

public class ConfigurableCarverConfig extends CaveCarverConfiguration {
	public static final Codec<ConfigurableCarverConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			CarverConfiguration.CODEC.forGetter(config -> config),
			FloatProvider.CODEC.fieldOf("horizontal_radius_multiplier").forGetter(config -> config.horizontalRadiusMultiplier),
			FloatProvider.CODEC.fieldOf("vertical_radius_multiplier").forGetter(config -> config.verticalRadiusMultiplier),
			FloatProvider.codec(-1, 1).fieldOf("floor_level").forGetter(config -> config.floorLevelProvider),
			BlockState.CODEC.optionalFieldOf("deep_filler_block", Blocks.LAVA.defaultBlockState()).forGetter(config -> config.deepFillerBlock)
	).apply(builder, ConfigurableCarverConfig::new));

	public final BlockState deepFillerBlock;
	public final FloatProvider floorLevelProvider;

	public ConfigurableCarverConfig(CarverConfiguration baseConfig, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel, BlockState deepFillerBlock) {
		super(baseConfig, horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel);

		this.floorLevelProvider = floorLevel;
		this.deepFillerBlock = deepFillerBlock;
	}
}
