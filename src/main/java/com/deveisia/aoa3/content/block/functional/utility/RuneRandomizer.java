package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LootUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

public class RuneRandomizer extends Block {
	public RuneRandomizer() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_CYAN).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() == AoAItems.UNPOWERED_RUNE.get() || heldItem.getItem() == AoAItems.CHARGED_RUNE.get()) {
			if (player instanceof ServerPlayer) {
				if (!player.isCreative())
					heldItem.shrink(1);

				ItemUtil.givePlayerMultipleItems(player, LootUtil.generateLoot((ServerLevel)world, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "misc/rune_randomizer"), LootUtil.getGiftContext((ServerLevel)world, BlockUtil.posToVec(pos), player)));

				player.level().playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_RUNE_RANDOMIZER_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
