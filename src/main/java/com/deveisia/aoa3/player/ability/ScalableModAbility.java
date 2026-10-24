package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.library.object.DynamicTextComponent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonObject;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.GsonHelper;

public abstract class ScalableModAbility extends AoAAbility.Instance {
	protected final float baseValue;
	protected final float perLevelMod;

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.baseValue = GsonHelper.getAsFloat(data, "base_value", 0);
		this.perLevelMod = GsonHelper.getAsFloat(data, "per_level_mod", 0);
	}

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, CompoundTag data) {
		super(ability, skill, data);

		this.baseValue = data.getFloat("base_value");
		this.perLevelMod = data.getFloat("per_level_mod");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String defaultKey = Util.makeDescriptionId("ability", RegistryUtil.getId(type())) + ".description";

		if (LocaleUtil.getKey(defaultDescription).equals(defaultKey) && defaultDescription.getContents() instanceof TranslatableContents && ((TranslatableContents)defaultDescription.getContents()).getArgs().length == 0) {
			MutableComponent component = Component.translatable(LocaleUtil.getKey(defaultDescription), getScalingDescriptionComponent(4));

			for (Component child : defaultDescription.getSiblings()) {
				component.append(child);
			}

			super.updateDescription(component);

			return;
		}

		super.updateDescription(defaultDescription);
	}

	protected MutableComponent getScalingDescriptionComponent(int precision) {
		return LocaleUtil.getAbilityValueDesc(baseValue != 0, perLevelMod != 0, isPercent(),
				NumberUtil.roundToNthDecimalPlace(baseValue * (isPercent() ? 100 : 1), precision),
				NumberUtil.roundToNthDecimalPlace(perLevelMod * (isPercent() ? 100 : 1), precision),
				DynamicTextComponent.create(() -> NumberUtil.roundToNthDecimalPlace(getScaledValue() * (isPercent() ? 100 : 1), precision)));
	}

	protected boolean isPercent() {
		return true;
	}

	protected boolean testAsChance() {
		return RandomUtil.percentChance(getScaledValue());
	}

	protected float getScaledValue() {
		if (perLevelMod == 0)
			return baseValue;

		return baseValue + skill.getLevel(false) * perLevelMod;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("base_value", this.baseValue);
			data.putFloat("per_level_mod", this.perLevelMod);
		}

		return data;
	}
}
