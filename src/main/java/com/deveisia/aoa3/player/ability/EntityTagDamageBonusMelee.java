package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.DamageUtil;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EntityTagDamageBonusMelee extends EntityTagDamageBonus {
	public EntityTagDamageBonusMelee(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	public EntityTagDamageBonusMelee(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".melee", getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag) && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
