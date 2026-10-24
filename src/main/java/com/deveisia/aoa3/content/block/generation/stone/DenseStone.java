package com.deveisia.aoa3.content.block.generation.stone;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.animal.ShikEntity;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class DenseStone extends Block {
	public DenseStone() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_LIGHT_GRAY).stats(1.5f, 10f).harvestTool(ToolType.PICKAXE).get());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(world, pos, state, player);

		if (!world.isClientSide && RandomUtil.oneInNChance(50) && !ItemUtil.hasEnchantment(Enchantments.SILK_TOUCH, player.getItemInHand(InteractionHand.MAIN_HAND))) {
			ShikEntity shik = new ShikEntity(AoAEntities.Animals.SHIK.get(), world);

			shik.teleportTo(pos.getX() + 0.5f, pos.getY() + 0.1f, pos.getZ() + 0.5f);
			world.addFreshEntity(shik);
		}
	}
}
