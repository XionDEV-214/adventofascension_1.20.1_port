package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class MineralizationStation extends Block {
	public MineralizationStation() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_BROWN).stats(5f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide && !player.getItemInHand(hand).isEmpty()) {
			ItemStack stack = player.getItemInHand(hand);
			Item tokensItem = null;
			int baseAmount = 5;

			switch (RegistryUtil.getId(stack.getItem()).getPath()) {
				case "blue_crystal":
				case "green_crystal":
				case "purple_crystal":
				case "red_crystal":
				case "white_crystal":
				case "yellow_crystal":
					baseAmount = 4;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "serenadium_ingot":
					baseAmount = 5;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "rosite_ingot":
				case "jade":
				case "jade_gem":
				case "amethyst":
				case "amethyst_shard":
					baseAmount = 6;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "sapphire":
				case "sapphire_gem":
				case "gemenyte":
				case "gemenyte_gem":
				case "jewelyte":
				case "jewelyte_gem":
				case "ornamyte":
				case "ornamyte_gem":
				case "skeletal_ingot":
				case "blazium_ingot":
				case "varsium_ingot":
				case "baronyte_ingot":
				case "emberstone_ingot":
					baseAmount = 7;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "crystallite":
				case "crystallite_gem":
				case "mystite_ingot":
				case "lyon_ingot":
					baseAmount = 9;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "bloodstone":
				case "bloodstone_gem":
				case "elecanium_ingot":
				case "ghastly_ingot":
				case "ghoulish_ingot":
				case "lunar_ingot":
					baseAmount = 10;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "shyregem":
				case "shyregem_gem":
				case "shyrestone_ingot":
					baseAmount = 11;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "blank_realmstone":
					player.setItemInHand(hand, new ItemStack(AoAItems.IROMINE_REALMSTONE.get()));
					player.inventoryMenu.broadcastChanges();
					return InteractionResult.SUCCESS;
				default:
					break;
			}

			if (tokensItem != null) {
				if (!player.isCreative())
					stack.shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, baseAmount + player.getRandom().nextInt(baseAmount)));
			}
		}

		return InteractionResult.SUCCESS;
	}
}
