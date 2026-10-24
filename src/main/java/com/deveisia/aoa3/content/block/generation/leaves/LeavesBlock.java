package com.deveisia.aoa3.content.block.generation.leaves;

import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class LeavesBlock extends net.minecraft.world.level.block.LeavesBlock {
	private final float saplingDropChance;

	public LeavesBlock(MapColor mapColour, float saplingChance) {
		super(new BlockUtil.CompactProperties(Material.LEAVES, mapColour).stats(0.2f).sound(SoundType.GRASS).noOcclusion().specialSpawns((state, world, pos, entity) -> entity == EntityType.OCELOT || entity == EntityType.PARROT).breathable().noScreenCover().get());

		this.saplingDropChance = saplingChance;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {}

	public float getSaplingDropChance() {
		return saplingDropChance;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}
}
