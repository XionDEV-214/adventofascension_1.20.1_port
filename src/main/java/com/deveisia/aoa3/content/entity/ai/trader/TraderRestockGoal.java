package com.deveisia.aoa3.content.entity.ai.trader;

import com.deveisia.aoa3.mixin.common.accessor.VillagerAccessor;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import net.minecraft.world.entity.ai.goal.Goal;

public class TraderRestockGoal extends Goal {
	private final AoATrader trader;

	public TraderRestockGoal(AoATrader trader) {
		this.trader = trader;
	}

	public boolean canUse() {
		return this.trader.isAlive() && !this.trader.isTrading() && this.trader.canRestock() && ((VillagerAccessor)this.trader).invokeAllowedToRestock();
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}

	@Override
	public void start() {
		if (trader.shouldRestock())
			trader.restock();

		stop();
	}
}
