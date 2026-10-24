package com.deveisia.aoa3.content.item.tool.shovel;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OccultShovel extends BaseShovel {
	public OccultShovel() {
		super(ItemUtil.customItemTier(3000, 11.0f, 6.0f, 6, 10, null),
				new Properties().durability(3000).rarity(Rarity.RARE));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return super.getDestroySpeed(stack, state) * (1 + (stack.getDamageValue() / (float)stack.getMaxDamage()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
