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

public class EntityTagDamageBonusProjectile extends EntityTagDamageBonus {
	public EntityTagDamageBonusProjectile(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_PROJECTILE.get(), skill, data);
	}

	public EntityTagDamageBonusProjectile(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_PROJECTILE.get(), skill, data);
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".projectile", getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag) && DamageUtil.isRangedDamage(ev.getSource(), ev.getEntity(), ev.getAmount()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
