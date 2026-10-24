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
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

public class AoACycleTrigger extends SimpleCriterionTrigger<AoACycleTrigger.Instance> {
	private static final ResourceLocation ID = AdventOfAscension.id("cycle");

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

		int cycle = json.has("cycle") ? Mth.clamp(GsonHelper.getAsInt(json, "cycle"), 1, 10) : 0;

		return new Instance(skill, cycle, player);
	}

	public void trigger(ServerPlayer player, AoASkill skill, int value) {
		trigger(player, instance -> instance.test(skill, value));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final int cycle;

		public Instance(@Nullable AoASkill skill, int cycle, ContextAwarePredicate player) {
			super(ID, player);

			this.skill = skill;
			this.cycle = cycle;
		}

		public Instance(@Nullable AoASkill skill, int cycle) {
			this(skill, cycle, ContextAwarePredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject obj = super.serializeToJson(context);

			if (skill != null)
				obj.addProperty("skill", RegistryUtil.getId(skill).toString());

			if (cycle > 0)
				obj.addProperty("cycle", cycle);

			return obj;
		}

		public boolean test(AoASkill skill, int value) {
			return (this.skill == null || this.skill == skill) && (this.cycle == 0 || value >= this.cycle);
		}
	}
}
