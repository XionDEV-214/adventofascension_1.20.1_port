package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.event.custom.events.ItemCraftingEvent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class BonusCraftingOutput extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFTING};

	@Nullable
	private final Item outputTarget;
	@Nullable
	private final TagKey<Item> outputTargetTag;

	public BonusCraftingOutput(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.has("item")) {
			outputTargetTag = null;
			outputTarget = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = TagKey.create(Registries.ITEM, ResourceLocation.parse(GsonHelper.getAsString(data, "tag")));
		}
	}

	public BonusCraftingOutput(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.contains("item")) {
			outputTargetTag = null;
			outputTarget = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(data.getString("item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = TagKey.create(Registries.ITEM, ResourceLocation.parse(data.getString("tag")));
		}
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		MutableComponent component;

		if (outputTarget != null) {
			component = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".item", getScalingDescriptionComponent(2), this.outputTarget.getDefaultInstance().getHoverName());
		}
		else {
			component = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".tag", getScalingDescriptionComponent(2), this.outputTargetTag.location().toString());
		}

		super.updateDescription(component);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemCrafting(ItemCraftingEvent ev) {
		if (outputTarget != null) {
			if (ev.getOutputStack().getItem() == outputTarget)
				ev.getOutputStack().setCount((int)Math.ceil(ev.getOutputStack().getCount() * (1 + getScaledValue())));
		}
		else {
			if (ev.getOutputStack().is(outputTargetTag))
				ev.getOutputStack().setCount((int)Math.ceil(ev.getOutputStack().getCount() * (1 + getScaledValue())));
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			if (outputTarget != null) {
				data.putString("item", RegistryUtil.getId(outputTarget).toString());
			}
			else {
				data.putString("tag", outputTargetTag.location().toString());
			}
		}

		return data;
	}
}
