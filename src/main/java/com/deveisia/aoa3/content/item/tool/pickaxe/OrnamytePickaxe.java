package com.deveisia.aoa3.content.item.tool.pickaxe;

import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class OrnamytePickaxe extends BasePickaxe {
	public OrnamytePickaxe() {
		super(ItemUtil.customItemTier(2750, 10.0f, 6.0f, 5, 14, null));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		float efficiency = super.getDestroySpeed(stack, state);

		return state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.CRYING_OBSIDIAN ? efficiency * 10f : efficiency;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
