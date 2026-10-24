package com.deveisia.aoa3.content.world.gen.feature.features.config;

import com.deveisia.aoa3.library.object.IntValueProvider;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class MiscStateAndVariablesConfig implements FeatureConfiguration {
	public static final Codec<MiscStateAndVariablesConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntValueProvider.CODEC.optionalFieldOf("count", new IntValueProvider(1)).forGetter(config -> config.count),
			IntValueProvider.CODEC.optionalFieldOf("x", new IntValueProvider(0)).forGetter(config -> config.x),
			IntValueProvider.CODEC.optionalFieldOf("y", new IntValueProvider(0)).forGetter(config -> config.y),
			IntValueProvider.CODEC.optionalFieldOf("z", new IntValueProvider(0)).forGetter(config -> config.z),
			Codec.BOOL.optionalFieldOf("inverted", false).forGetter(config -> config.inverted),
			BlockStateProvider.CODEC.optionalFieldOf("states", BlockStateProvider.simple(Blocks.AIR.defaultBlockState())).forGetter(config -> config.stateProvider))
			.apply(builder, MiscStateAndVariablesConfig::new));
	public final IntValueProvider count;
	public final IntValueProvider x;
	public final IntValueProvider y;
	public final IntValueProvider z;
	public final boolean inverted;
	public final BlockStateProvider stateProvider;

	public MiscStateAndVariablesConfig(IntValueProvider count, IntValueProvider x, IntValueProvider y, IntValueProvider z, boolean inverted, BlockStateProvider stateProvider) {
		this.count = count;
		this.x = x;
		this.y = y;
		this.z = z;
		this.inverted = inverted;
		this.stateProvider = stateProvider;
	}

	public static class Builder {
		private IntValueProvider count = new IntValueProvider(1);
		private IntValueProvider x = new IntValueProvider(0);
		private IntValueProvider y = new IntValueProvider(0);
		private IntValueProvider z = new IntValueProvider(0);
		private boolean inverted = false;
		private BlockStateProvider stateProvider = BlockStateProvider.simple(Blocks.AIR.defaultBlockState());
		private SimpleWeightedRandomList.Builder<BlockState> weightedStates = null;

		public Builder amount(int amount) {
			return amount(amount, amount);
		}

		public Builder amount(int min, int max) {
			this.count = new IntValueProvider(min, max);

			return this;
		}

		public Builder x(int amount) {
			return x(amount, amount);
		}

		public Builder x(int min, int max) {
			this.x = new IntValueProvider(min, max);

			return this;
		}

		public Builder y(int amount) {
			return y(amount, amount);
		}

		public Builder y(int min, int max) {
			this.y = new IntValueProvider(min, max);

			return this;
		}

		public Builder z(int amount) {
			return z(amount, amount);
		}

		public Builder z(int min, int max) {
			this.z = new IntValueProvider(min, max);

			return this;
		}

		public Builder generateDownwards() {
			this.inverted = true;

			return this;
		}

		public Builder singleBlock(BlockState state) {
			this.stateProvider = BlockStateProvider.simple(state);
			this.weightedStates = null;

			return this;
		}

		public Builder withBlock(BlockState state, int weight) {
			if (this.weightedStates == null)
				this.weightedStates = SimpleWeightedRandomList.builder();

			this.weightedStates.add(state, weight);
			this.stateProvider = new WeightedStateProvider(this.weightedStates);

			return this;
		}

		public MiscStateAndVariablesConfig build() {
			return new MiscStateAndVariablesConfig(count, x, y, z, inverted, stateProvider);
		}
	}
}
