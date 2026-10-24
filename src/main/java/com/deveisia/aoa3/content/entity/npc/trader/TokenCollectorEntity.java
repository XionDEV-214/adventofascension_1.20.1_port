package com.deveisia.aoa3.content.entity.npc.trader;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import com.deveisia.aoa3.util.WorldUtil;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;

public class TokenCollectorEntity extends AoATrader {
	public TokenCollectorEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);

		this.setInvulnerable(true);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.NOWHERE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return null;
	}

}
