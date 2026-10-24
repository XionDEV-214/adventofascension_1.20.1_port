package com.deveisia.aoa3.content.entity.npc.trader;

import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;

public class SkillMasterEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.SMALL_SKILL_CRYSTAL).cost(AoAItems.SILVER_COIN).xp(150).stock(32),
					BuildableTrade.trade(AoAItems.MEDIUM_SKILL_CRYSTAL).cost(AoAItems.GOLD_COIN).xp(150).stock(24),
					BuildableTrade.trade(AoAItems.LARGE_SKILL_CRYSTAL).cost(AoAItems.GOLD_COIN, 12).xp(150).stock(16),
					BuildableTrade.trade(AoAItems.GIANT_SKILL_CRYSTAL).cost(AoAItems.LUNAVER_COIN).xp(150).stock(12)).build();

	public SkillMasterEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return 4;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
		animationData.add(AoAAnimations.genericIdleController(this));
		animationData.add(new AnimationController<SkillMasterEntity>(this, "interaction", 0, event -> {
			if (isTrading()) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay("misc.interact").thenPlay("misc.interact.end"));

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		}));
	}
}
