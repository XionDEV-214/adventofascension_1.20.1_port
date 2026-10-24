package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.NumberUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.deveisia.aoa3.util.StringUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashSet;
import java.util.function.Consumer;
import com.deveisia.aoa3.mixin.common.accessor.MobEffectInstanceAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class PotionDurationReducer extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.POTION_APPLIED};

	private final float modifier;
	private final boolean useAddition;

	private final MobEffectCategory matchType;
	private final HashSet<ResourceLocation> matchEffects;

	private final Consumer<MobEffectInstance> modifierConsumer;

	public PotionDurationReducer(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = GsonHelper.getAsFloat(data, "modifier");
		this.useAddition = GsonHelper.getAsBoolean(data, "use_addition", false);

		if (data.has("match_effect_type")) {
			matchType = MobEffectCategory.valueOf(data.get("match_effect_type").getAsString());
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<ResourceLocation>();
			JsonElement effects = data.get("match_effects");

			if (effects.isJsonPrimitive()) {
				matchEffects.add(ResourceLocation.parse(effects.getAsString()));
			}
			else if (effects.isJsonArray()) {
				for (JsonElement element : effects.getAsJsonArray()) {
					matchEffects.add(ResourceLocation.parse(element.getAsString()));
				}
			}

			matchType = null;
		}

		this.modifierConsumer = effect -> {
			MobEffectInstanceAccessor accessor = (MobEffectInstanceAccessor)effect;

			if (useAddition) {
				accessor.setDurationRaw(accessor.getDurationRaw() + (int)modifier);
			}
			else {
				accessor.setDurationRaw((int)(accessor.getDurationRaw() * modifier));
			}
		};
	}

	public PotionDurationReducer(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = data.getFloat("modifier");
		this.useAddition = data.getBoolean("use_addition");

		if (data.contains("match_effect_type")) {
			matchType = MobEffectCategory.valueOf(data.getString("match_effect_type"));
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<ResourceLocation>();
			Tag effects = data.get("match_effects");

			if (effects.getType() == StringTag.TYPE) {
				matchEffects.add(ResourceLocation.parse(effects.getAsString()));
			}
			else if (effects.getType() == ListTag.TYPE) {
				for (Tag element : ((ListTag)effects)) {
					matchEffects.add(ResourceLocation.parse(element.getAsString()));
				}
			}

			matchType = null;
		}

		this.modifierConsumer = null;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String operationSuffix = useAddition ? ".addition" : ".multiply";
		String value = useAddition ? NumberUtil.roundToNthDecimalPlace(modifier, 2) : NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2);

		if (matchType != null) {
			super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription) + operationSuffix + ".type", StringUtil.toTitleCase(matchType.toString()), value));
		}
		else {
			super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription) + operationSuffix + ".list", value));
		}
	}

	@Override
	public void handleAppliedPotion(MobEffectEvent.Added ev) {
		MobEffectInstance effect = ev.getEffectInstance();

		if (!effectMatches(effect))
			return;

		reduceEffectDuration(effect, this.modifierConsumer);
	}

	private boolean effectMatches(MobEffectInstance effect) {
		if (matchType != null)
			return effect.getEffect().getCategory() == matchType;

		return matchEffects.contains(RegistryUtil.getId(effect.getEffect()));
	}

	private void reduceEffectDuration(MobEffectInstance effect, Consumer<MobEffectInstance> modifier) {
		modifier.accept(effect);

		MobEffectInstance hiddenEffect = ((MobEffectInstanceAccessor)effect).getHiddenEffectRaw();

		if (hiddenEffect != null)
			reduceEffectDuration(hiddenEffect, modifierConsumer);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("modifier", this.modifier);
			data.putBoolean("use_addition", this.useAddition);

			if (this.matchType != null) {
				data.putString("match_effect_type", this.matchType.toString());
			}
			else {
				ListTag effects = new ListTag();

				for (ResourceLocation id : this.matchEffects) {
					effects.add(StringTag.valueOf(id.toString()));
				}

				data.put("match_effects", effects);
			}
		}

		return data;
	}
}
