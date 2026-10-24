package com.deveisia.aoa3.content.item.tool.pickaxe;

import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.Tags;

public class Gemcracker extends BasePickaxe implements LootModifyingItem {
	public Gemcracker() {
		super(ItemUtil.customItemTier(2100, 10.0f, 6.0f, 6, 10, null));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (BlockUtil.isAirBlock(harvestedBlock) || existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.MINEABLE_WITH_PICKAXE) || !harvestedBlock.is(Tags.Blocks.ORES))
			return;

		ItemStack primaryStack = existingLoot.get(0);

		if (block.asItem() != primaryStack.getItem())
			existingLoot.addAll(ItemUtil.increaseStackSize(primaryStack, 1 + RandomUtil.randomNumberUpTo(primaryStack.getCount())));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
