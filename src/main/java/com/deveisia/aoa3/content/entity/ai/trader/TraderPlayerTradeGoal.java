package com.deveisia.aoa3.content.entity.ai.trader;

import com.deveisia.aoa3.content.entity.base.AoATrader;
import java.util.EnumSet;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class TraderPlayerTradeGoal extends Goal {
	private final AoATrader trader;

	public TraderPlayerTradeGoal(AoATrader trader) {
		this.trader = trader;

		setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean canUse() {
		if (!trader.isAlive() || trader.isInWater() || !trader.onGround() || trader.hurtMarked)
			return false;

		Player customer = trader.getTradingPlayer();

		if (customer == null || trader.distanceToSqr(customer) > 16.0D)
			return false;

		return customer.containerMenu != null;
	}

	public void start() {
		trader.getNavigation().stop();
	}

	public void stop() {
		trader.setTradingPlayer(null);
	}
}
