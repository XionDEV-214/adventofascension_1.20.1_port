package com.deveisia.aoa3.content.loottable.condition;

import com.deveisia.aoa3.common.registration.AoALootOperations;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class PlayerHasResource implements LootItemCondition {
	private final AoAResource resource;
	private final float amount;
	private final boolean consume;

	public PlayerHasResource(AoAResource resource, float amount, boolean consume) {
		this.resource = resource;
		this.amount = amount;
		this.consume = consume;
	}

	@Override
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.PLAYER_HAS_RESOURCE.get();
	}

	@Override
	public Set<LootContextParam<?>> getReferencedContextParams() {
		return ImmutableSet.of(LootContextParams.KILLER_ENTITY);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof ServerPlayer)
			return consume ? PlayerUtil.consumeResource((ServerPlayer)entity, resource, amount, false) : PlayerUtil.getResourceValue((ServerPlayer)entity, resource) >= amount;

		return false;
	}

	public AoAResource getResource() {
		return this.resource;
	}

	public float getAmount() {
		return this.amount;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<PlayerHasResource> {
		@Override
		public void serialize(JsonObject json, PlayerHasResource playerHasResource, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("resource", RegistryUtil.getId(playerHasResource.resource).toString());
			json.addProperty("amount", playerHasResource.amount);
			json.addProperty("consume", playerHasResource.consume);
		}

		@Override
		public PlayerHasResource deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasResource(AoAResources.getResource(ResourceLocation.parse(GsonHelper.getAsString(json, "resource"))), GsonHelper.getAsFloat(json, "amount"), GsonHelper.isValidNode(json, "consume") && GsonHelper.getAsBoolean(json, "consume"));
		}
	}
}
