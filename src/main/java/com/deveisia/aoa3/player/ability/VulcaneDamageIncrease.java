package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class VulcaneDamageIncrease extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING};

	private final float minRage;
	private final float modifier;

	public VulcaneDamageIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.VULCANE_DAMAGE_INCREASE.get(), skill, data);

		this.minRage = GsonHelper.getAsFloat(data, "min_rage", 0);
		this.modifier = GsonHelper.getAsFloat(data, "modifier");
	}

	public VulcaneDamageIncrease(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.VULCANE_DAMAGE_INCREASE.get(), skill, data);

		this.minRage = data.getFloat("min_rage");
		this.modifier = data.getFloat("modifier");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription) + (minRage > 0 ? ".minRage" : ""), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2), NumberUtil.roundToNthDecimalPlace(minRage, 2)));
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (DamageUtil.isVulcaneDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * this.modifier);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("min_rage", this.minRage);
			data.putFloat("modifier", this.modifier);
		}

		return data;
	}
}
