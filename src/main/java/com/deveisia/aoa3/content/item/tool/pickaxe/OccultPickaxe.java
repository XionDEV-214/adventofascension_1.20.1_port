package com.deveisia.aoa3.content.item.tool.pickaxe;

import com.deveisia.aoa3.client.ClientOperations;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.event.GlobalEvents;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class OccultPickaxe extends BasePickaxe {
	public OccultPickaxe() {
		super(ItemUtil.customItemTier(3000, 11.0f, 6.0f, 6, 10, null),
				AoAItemGroups.TOOLS.properties().durability(3000).rarity(Rarity.RARE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (world.isClientSide()) {
			ArrayList<Pair<BlockPos, BlockState>> blocks = new ArrayList<Pair<BlockPos, BlockState>>();

			for (int i = (int)(player.getX() - 4.0); i < (int)(player.getX() + 4.0); ++i) {
				for (int j = (int)(player.getY() - 4.0); j < (int)(player.getY() + 4.0); ++j) {
					for (int k = (int)(player.getZ() - 4.0); k < (int)(player.getZ() + 4.0); ++k) {
						BlockPos pos = BlockPos.containing(i, j, k);
						BlockState state = world.getBlockState(pos);

						if (state.is(Tags.Blocks.ORES))
							blocks.add(Pair.of(pos, state));
					}
				}
			}

			ClientOperations.addOccultBlocks(GlobalEvents.tick + 150, blocks);
		}

		InteractionResultHolder<ItemStack> result = super.use(world, player, hand);

		if (result.getResult() == InteractionResult.FAIL)
			return InteractionResultHolder.pass(result.getObject());

		return result;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
