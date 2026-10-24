package com.deveisia.aoa3.content.loottable.function;

import com.deveisia.aoa3.common.registration.AoALootOperations;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class GrantSkillXp extends LootItemConditionalFunction {
	private final AoASkill skill;
	private final float xp;

	protected GrantSkillXp(LootItemCondition[] lootConditions, AoASkill skill, float xp) {
		super(lootConditions);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	public LootItemFunctionType getType() {
		return AoALootOperations.LootFunctions.GRANT_SKILL_XP.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		Entity entity = context.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (!(entity instanceof Player))
			entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof ServerPlayer)
			PlayerUtil.getAdventPlayer((ServerPlayer)entity).getSkill(skill).adjustXp(xp, false, false);

		return stack;
	}

	public AoASkill getSkill() {
		return this.skill;
	}

	public float getXp() {
		return this.xp;
	}

	public static Builder<?> builder(AoASkill skill, float xp) {
		return simpleBuilder((conditions) -> new GrantSkillXp(conditions, skill, xp));
	}

	public static class Serializer extends LootItemConditionalFunction.Serializer<GrantSkillXp> {
		@Override
		public void serialize(JsonObject object, GrantSkillXp function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.addProperty("skill", RegistryUtil.getId(function.skill).toString());
			object.addProperty("xp", function.xp);
		}

		@Override
		public GrantSkillXp deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditions) {
			return new GrantSkillXp(conditions, AoASkills.getSkill(ResourceLocation.parse(GsonHelper.getAsString(object, "skill"))), GsonHelper.getAsFloat(object, "xp"));
		}
	}
}
