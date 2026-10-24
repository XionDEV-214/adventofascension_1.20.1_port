package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.event.custom.events.PlayerLevelChangeEvent;
import com.deveisia.aoa3.library.object.DynamicTextComponent;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.deveisia.aoa3.util.StringUtil;
import com.google.gson.JsonObject;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.ForgeRegistries;
import static com.deveisia.aoa3.player.AoAPlayerEventListener.ListenerType.ATTRIBUTE_MODIFIERS;
import static com.deveisia.aoa3.player.AoAPlayerEventListener.ListenerType.LEVEL_CHANGE;

public class AttributeModification extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ATTRIBUTE_MODIFIERS, LEVEL_CHANGE};

	private final Attribute attribute;
	private final AttributeModifier modifier;

	private float loginHealth = -1;

	public AttributeModification(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "attribute")));
		this.modifier = new AttributeModifier(UUID.randomUUID(), getUniqueIdentifier(), 0, AttributeModifier.Operation.fromValue(GsonHelper.getAsInt(data, "operation"))) {
			@Override
			public double getAmount() {
				return getScaledValue();
			}
		};
	}

	public AttributeModification(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.parse(data.getString("attribute")));
		this.modifier = new AttributeModifier(data.getUUID("uuid"), getUniqueIdentifier(), 0, AttributeModifier.Operation.fromValue(data.getInt("operation"))) {
			@Override
			public double getAmount() {
				return getScaledValue();
			}
		};
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String amount = "";
		String perLevel = "";

		switch (this.modifier.getOperation()) {
			case MULTIPLY_BASE:
			case MULTIPLY_TOTAL:
				if (baseValue != 0)
					amount = "+" + NumberUtil.roundToNthDecimalPlace(baseValue * 100, 3);

				if (perLevelMod != 0)
					perLevel = NumberUtil.roundToNthDecimalPlace(this.perLevelMod * 100, 3);

				break;
			case ADDITION:
			default:
				if (baseValue != 0)
					amount = NumberUtil.roundToNthDecimalPlace(baseValue, 3);

				if (perLevelMod != 0)
					perLevel = NumberUtil.roundToNthDecimalPlace(this.perLevelMod, 3);

				break;
		}

		super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription),
				StringUtil.toTitleCase(attribute.getDescriptionId().substring(attribute.getDescriptionId().lastIndexOf(".") + 1)),
				LocaleUtil.getAbilityValueDesc(baseValue != 0, perLevelMod != 0, modifier.getOperation() != AttributeModifier.Operation.ADDITION, amount, perLevel, DynamicTextComponent.create(() -> NumberUtil.roundToNthDecimalPlace((float)modifier.getAmount() * (modifier.getOperation() == AttributeModifier.Operation.ADDITION ? 1 : 100), 3)))));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void applyAttributeModifiers(ServerPlayerDataManager plData) {
		EntityUtil.reapplyAttributeModifier(plData.player(), attribute, modifier, false);

		if (loginHealth > 0) {
			plData.player().setHealth(loginHealth);
			loginHealth = -1;
		}
	}

	@Override
	public void removeAttributeModifiers(ServerPlayerDataManager plData) {
		EntityUtil.removeAttributeModifier(plData.player(), attribute, modifier.getId());
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		EntityUtil.reapplyAttributeModifier(ev.getEntity(), attribute, modifier, false);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putString("attribute", RegistryUtil.getId(attribute).toString());
			data.putInt("operation", this.modifier.getOperation().toValue());
			data.putUUID("uuid", this.modifier.getId());
		}

		return data;
	}

	@Override
	public CompoundTag saveToNbt() {
		CompoundTag data = super.saveToNbt();

		if (attribute == Attributes.MAX_HEALTH) {
			double health = getPlayer().getHealth();

			if (health > 0)
				data.putDouble("current_health", getPlayer().getHealth());
		}

		return data;
	}

	@Override
	public void loadFromNbt(CompoundTag data) {
		super.loadFromNbt(data);

		if (attribute == Attributes.MAX_HEALTH && getListenerState() == ListenerState.ACTIVE && data.contains("current_health")) {
			if (getLevelReq() == 1) {
				loginHealth = (float)data.getDouble("current_health");
			}
			else {
				getPlayer().setHealth((float)data.getDouble("current_health"));
			}
		}
	}
}
