package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.registration.AoAArmour;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class PetalCraftingStation extends Block {
	public PetalCraftingStation() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_PURPLE).stats(5f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.PETALS.get()) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				switch (player.getRandom().nextInt(4)) {
					case 0:
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.boots.get()));
						break;
					case 1:
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.leggings.get()));
						break;
					case 2:
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.chestplate.get()));
						break;
					case 3:
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.helmet.get()));
						break;
				}

				world.playSound(null, pos, AoASounds.BLOCK_PETAL_CRAFTING_STATION_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
