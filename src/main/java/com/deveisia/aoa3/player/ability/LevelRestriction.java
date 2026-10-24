package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.data.server.AoASkillReqReloadListener;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class LevelRestriction extends AoAAbility.Instance {
	private final ListenerType[] LISTENERS = new ListenerType[0];

	private final ResourceLocation restrictedId;

	public LevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = generateRestrictionHandlers(data);
	}

	public LevelRestriction(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = ResourceLocation.parse(data.getString("restriction_id"));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(this.restrictedId);

		if (restrictions.isEmpty()) {
			super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription), "??"));

			return;
		}

		Component targetName;

		if (isForBlock(restrictions)) {
			targetName = ForgeRegistries.BLOCKS.getValue(restrictedId).getName();
		}
		else {
			Item item = ForgeRegistries.ITEMS.getValue(restrictedId);
			targetName = item.getName(item.getDefaultInstance());
		}

		MutableComponent description = Component.translatable(LocaleUtil.getKey(defaultDescription), targetName);
		boolean comma = false;

		for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> restriction : restrictions.entrySet()) {
			if (comma)
				description.append(", ");

			description.append(Component.translatable(LocaleUtil.getKey(defaultDescription) + "." + restriction.getKey()));

			comma = true;
		}

		super.updateDescription(description);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("restriction_id", this.restrictedId.toString());

		return data;
	}

	protected ResourceLocation generateRestrictionHandlers(JsonObject json) {
		ResourceLocation restrictedId = ResourceLocation.parse(GsonHelper.getAsString(json, "restricted_id"));
		JsonArray reqs = GsonHelper.getAsJsonArray(json, "restrictions");

		if (reqs.size() == 0)
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		Map<String, List<Pair<ResourceLocation, Integer>>> reqData = new HashMap<String, List<Pair<ResourceLocation, Integer>>>();

		for (JsonElement entry : reqs) {
			reqData.put(entry.getAsJsonPrimitive().getAsString(), Collections.singletonList(Pair.of(RegistryUtil.getId(skill.type()), getLevelReq())));
		}

		AoASkillReqReloadListener.addRequirements(restrictedId, reqData);

		return restrictedId;
	}

	private boolean isForBlock(Map<String, List<Pair<ResourceLocation, Integer>>> restrictions) {
		for (String type : restrictions.keySet()) {
			switch (type) {
				case "break_block":
				case "place_block":
				case "interact_with":
					return true;
				default:
					break;
			}
		}

		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
