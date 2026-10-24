package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class EmberstoneAxe extends BaseAxe implements LootModifyingItem {
	public EmberstoneAxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 11f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty())
			return;

		if (harvestedBlock.is(BlockTags.LOGS) && harvestedBlock.is(BlockTags.MINEABLE_WITH_AXE)) {
			ServerLevel world = lootContext.getLevel();
			BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));
			ItemStack blockDrop = ItemStack.EMPTY;
			Item blockItem = Item.byBlock(block);
			Iterator<ItemStack> stackIterator = existingLoot.iterator();

			while (stackIterator.hasNext()) {
				ItemStack stack = stackIterator.next();

				if (stack.getItem() == blockItem) {
					blockDrop = stack;

					stackIterator.remove();
					break;
				}
			}

			if (blockDrop != ItemStack.EMPTY) {
				block.popExperience(world, pos, (1 + RandomUtil.RANDOM.source().nextInt(3)) * blockDrop.getCount());

				for (int i = 0; i < 5; i++) {
					world.sendParticles(ParticleTypes.FLAME, pos.getX() + RandomUtil.RANDOM.source().nextFloat(), pos.getY() + RandomUtil.RANDOM.source().nextFloat(), pos.getZ() + RandomUtil.RANDOM.source().nextFloat(), 1, 0, 0, 0, 0);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
