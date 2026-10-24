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

public class AoALevelUpTrigger extends SimpleCriterionTrigger<AoALevelUpTrigger.Instance> {
	private static final ResourceLocation ID = AdventOfAscension.id("level_up");

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

		int level = json.has("level") ? Mth.clamp(GsonHelper.getAsInt(json, "level"), 1, 1000) : 0;

		return new Instance(skill, level, player);
	}

	public void trigger(ServerPlayer player, AoASkill skill, int value) {
		trigger(player, instance -> instance.test(skill, value));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final int level;

		public Instance(@Nullable AoASkill skill, int level, ContextAwarePredicate player) {
			super(ID, player);

			this.skill = skill;
			this.level = Mth.clamp(level, 1, 1000);
		}

		public Instance(@Nullable AoASkill skill, int level) {
			this(skill, level, ContextAwarePredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject obj = super.serializeToJson(context);

			if (skill != null)
				obj.addProperty("skill", RegistryUtil.getId(skill).toString());

			if (level > 0)
				obj.addProperty("level", level);

			return obj;
		}

		public boolean test(AoASkill skill, int value) {
			return (this.skill == null || this.skill == skill) && (this.level == 0 || value >= this.level);
		}
	}
}
