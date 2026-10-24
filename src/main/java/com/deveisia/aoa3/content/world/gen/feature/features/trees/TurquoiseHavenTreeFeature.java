package com.deveisia.aoa3.content.world.gen.feature.features.trees;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.block.functional.plant.SaplingBlock;
import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class TurquoiseHavenTreeFeature extends HavenTreeFeature {
	public TurquoiseHavenTreeFeature(Codec<BlockStateConfiguration> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.TURQUOISE_HAVEN_LEAVES.get().defaultBlockState();
	}
}
