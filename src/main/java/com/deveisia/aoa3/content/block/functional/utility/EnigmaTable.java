package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class EnigmaTable extends Block {
	public EnigmaTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.TERRACOTTA_BLACK).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide && WorldUtil.isWorld(world, AoADimensions.VOX_PONDS.key)) {
			for (int i = 0; i < 4; i++) {
				ItemEntity unchargedStone = new ItemEntity(world, pos.getX(), pos.getY() + 0.2, pos.getZ(), new ItemStack(AoAItems.UNCHARGED_STONE.get()));

				unchargedStone.setPickUpDelay(10);
				unchargedStone.push(RandomUtil.randomGaussianValue(), 1 + RandomUtil.randomValueUpTo(1d), RandomUtil.randomGaussianValue());
				world.addFreshEntity(unchargedStone);
			}

			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}

		return InteractionResult.SUCCESS;
	}
}
