package com.deveisia.aoa3.advancement.trigger;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

import javax.annotation.Nullable;

public class AoAXpGainTrigger extends SimpleCriterionTrigger<AoAXpGainTrigger.Instance> {
	private static final ResourceLocation ID = AdventOfAscension.id("gain_xp");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected Instance createInstance(JsonObject json, ContextAwarePredicate player, DeserializationContext context) {
		AoASkill skill = null;

		if (json.has("skill")) {
			ResourceLocation skillId = ResourceLocation.parse(GsonHelper.getAsString(json, "skill"));
			skill = AoASkills.getSkill(skillId);

			if (skill == null)
				throw new IllegalArgumentException("Invalid AoASkill ID: '" + skillId + "'");
		}

		float xp = json.has("xp") ? GsonHelper.getAsFloat(json, "xp") : 0f;

		return new Instance(skill, xp, player);
	}

	public void trigger(ServerPlayer player, AoASkill skill, float value) {
		trigger(player, instance -> instance.test(skill, value));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final float xp;

		public Instance(@Nullable AoASkill skill, float xp, ContextAwarePredicate player) {
			super(ID, player);

			this.skill = skill;
			this.xp = xp;
		}

		public Instance(@Nullable AoASkill skill, float xp) {
			this(skill, xp, ContextAwarePredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject obj = super.serializeToJson(context);

			if (skill != null)
				obj.addProperty("skill", RegistryUtil.getId(skill).toString());

			if (xp > 0)
				obj.addProperty("xp", xp);

			return obj;
		}

		public boolean test(AoASkill skill, float value) {
			return (this.skill == null || this.skill == skill) && (this.xp == 0 || this.xp <= value);
		}
	}
}
