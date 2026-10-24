package com.deveisia.aoa3.content.entity.npc.lottoman;

import com.deveisia.aoa3.content.entity.base.AoATrader;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;

public class WitheringLottomanEntity extends AoATrader {
	public WitheringLottomanEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
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
