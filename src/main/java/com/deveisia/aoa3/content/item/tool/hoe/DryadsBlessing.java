package com.deveisia.aoa3.content.item.tool.hoe;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

public class DryadsBlessing extends HoeItem implements LootModifyingItem {
	public DryadsBlessing() {
		super(ItemUtil.customItemTier(3020, 10f, 3f, 5, 18, null),
				-4,
				0,
				AoAItemGroups.TOOLS.properties());
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (existingLoot.isEmpty() || !(block instanceof CropBlock))
			return;

		if (!((CropBlock)block).isMaxAge(harvestedBlock))
			return;

		for (ListIterator<ItemStack> iterator = existingLoot.listIterator(); iterator.hasNext();) {
			ItemStack itStack = iterator.next();

			ItemUtil.increaseStackSize(itStack, itStack.getCount()).forEach(iterator::add);
		}
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
		pTooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}
}
