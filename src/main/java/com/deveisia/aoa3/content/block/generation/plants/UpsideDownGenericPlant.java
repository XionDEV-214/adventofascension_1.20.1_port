package com.deveisia.aoa3.content.block.generation.plants;

import com.deveisia.aoa3.library.compat.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UpsideDownGenericPlant extends GenericPlantBlock {
	private static VoxelShape SHAPE = Shapes.create(new AABB(0.1, 0.1, 0.1, 1, 1, 1));

	public UpsideDownGenericPlant(Material material, MapColor mapColour, SoundType sound, Material... growthMaterials) {
		super(material, mapColour, sound, growthMaterials);
	}

	public UpsideDownGenericPlant(Material material, MapColor mapColour, Material... growthMaterials) {
		super(material, mapColour, growthMaterials);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.above());

		return (growthMaterials.isEmpty() || growthMaterials.contains(Material.of(targetState))) && targetState.isSolidRender(world, pos.below());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
