package com.deveisia.aoa3.content.block.generation.log;

import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.ToolAction;
import net.minecraft.world.item.context.UseOnContext;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class LogBlock extends RotatedPillarBlock {
	private final Supplier<BlockState> strippedBlock;

	public LogBlock(MapColor mapColour) {
		this(mapColour, mapColour);
	}

	public LogBlock(MapColor mapColour, Supplier<Block> strippedBlock) {
		this(mapColour, mapColour, strippedBlock);
	}

	public LogBlock(MapColor mapColour, MapColor logEndMapColour) {
		this(mapColour, logEndMapColour, null);
	}

	public LogBlock(MapColor mapColour, MapColor logEndMapColour, Supplier<Block> strippedBlock) {
		super(new BlockUtil.CompactProperties(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? logEndMapColour : mapColour).stats(2f).tool(ToolType.AXE).get());

		this.strippedBlock = strippedBlock == null ? null : () -> strippedBlock.get().defaultBlockState();
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (strippedBlock == null || toolAction != ToolActions.AXE_STRIP)
			return super.getToolModifiedState(state, context, toolAction, simulate);

		return strippedBlock.get().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
	}
}
