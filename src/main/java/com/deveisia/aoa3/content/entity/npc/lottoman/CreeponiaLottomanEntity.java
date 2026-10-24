package com.deveisia.aoa3.content.entity.npc.lottoman;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import com.deveisia.aoa3.util.WorldUtil;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CreeponiaLottomanEntity extends AoATrader {
	public CreeponiaLottomanEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.CREEPONIA.key);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.BLANK_REALMSTONE.get() && heldStack.getItem().interactLivingEntity(heldStack, player, this, hand).consumesAction())
			return InteractionResult.SUCCESS;

		return super.mobInteract(player, hand);
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return newProfessionLevel == 1 ? 4 : 2;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return LottomanEntity.TRADES;
	}
}
