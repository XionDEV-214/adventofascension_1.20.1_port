package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.content.block.generation.log.LogBlock;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OrnamyteAxe extends BaseAxe {
	public OrnamyteAxe() {
		super(ItemUtil.customItemTier(2750, 10.0f, 11.5f, 5, 14, null));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return Material.of(state) == Material.WOOD && (!state.is(BlockTags.LOGS) && !(state.getBlock() instanceof LogBlock)) ? super.getDestroySpeed(stack, state) * 10 : super.getDestroySpeed(stack, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
