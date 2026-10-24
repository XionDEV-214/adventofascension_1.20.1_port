package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class OccultAxe extends BaseAxe {
	public OccultAxe() {
		super(ItemUtil.customItemTier(3000, 11.0f, 11.5f, 6, 10, null),
				new Properties().durability(3000).rarity(Rarity.RARE));
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
		if (entity instanceof Player && state.is(BlockTags.LOGS)) {
			BlockPos breakPos = pos;
			Block originBlock = state.getBlock();
			ItemStack toolStack = entity.getItemInHand(InteractionHand.MAIN_HAND);

			while (world.getBlockState(breakPos = breakPos.above()).getBlock() == originBlock && !toolStack.isEmpty()) {
				WorldUtil.harvestAdditionalBlock(world, (Player)entity, breakPos, false);
			}
		}

		return super.mineBlock(stack, world, state, pos, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
