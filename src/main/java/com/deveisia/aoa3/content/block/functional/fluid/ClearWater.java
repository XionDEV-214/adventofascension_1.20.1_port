package com.deveisia.aoa3.content.block.functional.fluid;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class ClearWater extends LiquidBlock {
	public ClearWater(Supplier<? extends FlowingFluid> supplier, Properties properties) {
		super(supplier, properties);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
}
