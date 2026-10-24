package com.deveisia.aoa3.content.block.generation.ore;

import net.minecraft.util.RandomSource;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class OreBlock extends Block {
	private final int minXp;
	private final int maxXp;

	public OreBlock(MapColor mapColour, int minXp, int maxXp, int harvestLevel) {
		super(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(5f, 10f).harvestTool(ToolType.PICKAXE, harvestLevel).get());

		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public OreBlock(MapColor mapColour, int harvestLevel) {
		this(mapColour, 0, 0, harvestLevel);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader world, RandomSource randomSource, BlockPos pos, int fortune, int silktouch) {
		if (silktouch > 0 || maxXp == 0)
			return 0;

		return (int)(RandomUtil.randomNumberBetween(minXp, maxXp) * (1 + 0.15f * fortune));
	}
}
