package com.deveisia.aoa3.content.item.tool.shovel;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

public class SkeletalShovel extends BaseShovel implements LootModifyingItem {
	public SkeletalShovel() {
		super(ItemUtil.customItemTier(2000, 10.0f, 6.0f, 5, 10, null));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.MINEABLE_WITH_SHOVEL))
			return;

		if (RandomUtil.oneInNChance(3)) {
			int dropChoice = RandomUtil.randomNumberUpTo(50);
			ItemStack drop;

			if (dropChoice == 0) {
				drop = new ItemStack(RandomUtil.getRandomSelection(
						AoAItems.SKULLBONE_FRAGMENT.get(),
						AoAItems.CHESTBONE_FRAGMENT.get(),
						AoAItems.LEGBONE_FRAGMENT.get(),
						AoAItems.FOOTBONE_FRAGMENT.get()));
			}
			else if (dropChoice < 10) {
				drop = new ItemStack(Items.BONE_MEAL);
			}
			else {
				drop = new ItemStack(Items.BONE);
			}

			existingLoot.add(drop);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.skeletal", LocaleUtil.ItemDescriptionType.UNIQUE));
	}
}
