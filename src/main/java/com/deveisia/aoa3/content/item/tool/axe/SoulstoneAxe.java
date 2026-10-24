package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class SoulstoneAxe extends BaseAxe implements LootModifyingItem {
	public SoulstoneAxe() {
		super(ItemUtil.customItemTier(2000, 11.0f, 11f, 6, 10, null));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.MINEABLE_WITH_AXE) || !harvestedBlock.is(BlockTags.LOGS) || !lootContext.hasParam(LootContextParams.THIS_ENTITY))
			return;

		Entity harvestingPlayer = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (!(harvestingPlayer instanceof ServerPlayer))
			return;

		ServerLevel world = lootContext.getLevel();
		BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));
		ItemStack blockDrop = ItemStack.EMPTY;
		Item blockItem = Item.byBlock(block);

		for (ItemStack stack : existingLoot) {
			if (stack.getItem() == blockItem) {
				blockDrop = stack;

				break;
			}
		}

		if (blockDrop == ItemStack.EMPTY)
			blockDrop = existingLoot.get(0);

		if (blockDrop != ItemStack.EMPTY && PlayerUtil.consumeResource((ServerPlayer)harvestingPlayer, AoAResources.SPIRIT.get(), 5, false)) {
			blockDrop.setCount(blockDrop.getCount() * 2);

			for (int i = 0; i < 5; i++) {
				world.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + RandomUtil.RANDOM.source().nextFloat(), pos.getY() + RandomUtil.RANDOM.source().nextFloat(), pos.getZ() + RandomUtil.RANDOM.source().nextFloat(), 1, 0, 0, 0, 0);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
