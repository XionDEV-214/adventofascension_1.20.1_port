package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.DamageUtil;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import static com.deveisia.aoa3.player.AoAPlayerEventListener.ListenerType.HAULING_ROD_PULL_ENTITY;

public class HaulingRodPullDamage extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {HAULING_ROD_PULL_ENTITY};

	public HaulingRodPullDamage(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_ROD_PULL_DAMAGE.get(), skill, data);
	}

	public HaulingRodPullDamage(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HAULING_ROD_PULL_DAMAGE.get(), skill, data);
	}

	@Override
	protected boolean isPercent() {
		return false;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHaulingRodPullEntity(HaulingRodPullEntityEvent ev) {
		if (ev.getHookedEntity() instanceof LivingEntity)
			DamageUtil.dealHaulingDamage(getPlayer(), ev.getBobber(), ev.getHookedEntity(), getScaledValue());
	}
}
