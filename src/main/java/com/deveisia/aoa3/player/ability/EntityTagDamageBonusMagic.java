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

public class EntityTagDamageBonusMagic extends EntityTagDamageBonus {
	public EntityTagDamageBonusMagic(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MAGIC.get(), skill, data);
	}

	public EntityTagDamageBonusMagic(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MAGIC.get(), skill, data);
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".magic", getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag) && DamageUtil.isMagicDamage(ev.getSource(), ev.getEntity(), ev.getAmount()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
