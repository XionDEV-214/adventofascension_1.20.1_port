package com.deveisia.aoa3.content.block.generation.misc;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ToxicBlock extends Block {
	private final VoxelShape SHAPE = Shapes.create(new AABB(0.002, 0.002, 0.002, 0.998, 0.998, 0.998));

	public ToxicBlock() {
		super(new BlockUtil.CompactProperties(Material.DIRT, MapColor.TERRACOTTA_GREEN).stats(1.5f, 1f).tool(ToolType.SHOVEL).sound(SoundType.SLIME_BLOCK).get());
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity instanceof Player && !((Player)entity).isCreative()) {
			entity.hurt(AoADamageTypes.source(world, AoADamageTypes.ACID), 4);
			EntityUtil.applyPotions(entity,
					new EffectBuilder(MobEffects.POISON, 60).level(6),
					new EffectBuilder(MobEffects.CONFUSION, 40),
					new EffectBuilder(MobEffects.BLINDNESS, 40));
		}
	}
}
