package com.deveisia.aoa3.player.ability;

import net.minecraft.tags.TagKey;
import com.deveisia.aoa3.util.LocaleUtil;
import net.minecraft.core.registries.Registries;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EntityTagDamageBonus extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING};

	protected final TagKey<EntityType<?>> tag;

	public EntityTagDamageBonus(AoASkill.Instance skill, JsonObject data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoASkill.Instance skill, CompoundTag data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.tag = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(GsonHelper.getAsString(data, "tag")));
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, CompoundTag data) {
		super(ability, skill, data);

		this.tag = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(data.getString("tag")));
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(LocaleUtil.getKey(defaultDescription), getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
