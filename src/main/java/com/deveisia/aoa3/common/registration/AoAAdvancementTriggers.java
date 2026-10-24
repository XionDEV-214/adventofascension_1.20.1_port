package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advancement.trigger.AoACycleTrigger;
import com.deveisia.aoa3.advancement.trigger.AoALevelUpTrigger;
import com.deveisia.aoa3.advancement.trigger.AoAXpGainTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;

public final class AoAAdvancementTriggers {
	public static final AoALevelUpTrigger levelUpTrigger = new AoALevelUpTrigger();
	public static final AoAXpGainTrigger xpGainTrigger = new AoAXpGainTrigger();
	public static final AoACycleTrigger cycleTrigger = new AoACycleTrigger();

	public static void registerTriggers() {
		registerTriggers(
				levelUpTrigger,
				xpGainTrigger,
				cycleTrigger
		);
	}

	private static void registerTriggers(CriterionTrigger<?>... triggers) {
		for (CriterionTrigger<?> trigger : triggers) {
			CriteriaTriggers.register(trigger);
		}
	}
}
