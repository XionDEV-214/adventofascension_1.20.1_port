package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.event.custom.events.ItemSmeltingEvent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class BonusSmeltResult extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_SMELTING};

	private final RandomSource random = RandomSource.create();
	private final int uniqueIdHash;

	public BonusSmeltResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	public BonusSmeltResult(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemSmelting(ItemSmeltingEvent ev) {
		ItemStack smeltedStack = ev.getOutputStack();

		if (smeltedStack.getItem().getFoodProperties() == null) {
			random.setSeed(this.uniqueIdHash * ev.getEntity().level().getGameTime() >> 4);
			random.setSeed(random.nextLong());
			random.setSeed(random.nextLong());

			if (random.nextFloat() < getScaledValue())
				smeltedStack.setCount(smeltedStack.getCount() + 1);
		}
	}
}
