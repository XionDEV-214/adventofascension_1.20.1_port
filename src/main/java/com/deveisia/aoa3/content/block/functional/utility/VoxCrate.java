package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.npc.lottoman.ToxicLottomanEntity;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class VoxCrate extends Block {
	public VoxCrate() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MapColor.TERRACOTTA_GREEN).stats(5f, 3f).tool(ToolType.AXE).get());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(world, pos, state, player);

		if (!world.isClientSide && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getItemInHand(InteractionHand.MAIN_HAND)) == 0) {
			ToxicLottomanEntity lottoman = new ToxicLottomanEntity(AoAEntities.NPCs.TOXIC_LOTTOMAN.get(), world);

			lottoman.moveTo(pos.getX(), pos.getY() + 0.5, pos.getZ(), 0, 0);
			lottoman.finalizeSpawn((ServerLevel)world, world.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
			world.addFreshEntity(lottoman);
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(AoAEntities.NPCs.TOXIC_LOTTOMAN.get().getDescriptionId() + ".spawn"));
		}
	}
}
