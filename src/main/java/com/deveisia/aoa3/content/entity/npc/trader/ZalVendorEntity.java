package com.deveisia.aoa3.content.entity.npc.trader;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import com.deveisia.aoa3.util.WorldUtil;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;

public class ZalVendorEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.LUNARADE_MUG).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.LUNARADE).cost(AoAItems.COPPER_COIN, 5).xp(2).stock(64))
			.trades(2,
					BuildableTrade.trade(AoAItems.POP_SHOT, 2).cost(AoAItems.COPPER_COIN, 4).stock(32),
					BuildableTrade.trade(AoAWeapons.SLINGSHOT).cost(AoAItems.GOLD_COIN).xp(40).stock(5)).build();

	public ZalVendorEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
