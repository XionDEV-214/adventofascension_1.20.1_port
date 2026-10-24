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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class GorbArmsDealerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 2).cost(Items.ARROW))
			.trades(2,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 7).cost(AoAWeapons.RUNIC_BOMB).xp(5),
					BuildableTrade.trade(AoAWeapons.GRENADE, 5).cost(AoAItems.SILVER_COIN).xp(15))
			.trades(3,
					BuildableTrade.trade(AoAWeapons.LASER_BLASTER).cost(AoAItems.GOLD_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAItems.WEAPON_PARTS).cost(AoAItems.GOLD_COIN, 10).xp(100).stock(1)).build();

	public GorbArmsDealerEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.5f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.MYSTERIUM.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
