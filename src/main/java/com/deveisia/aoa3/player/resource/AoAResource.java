package com.deveisia.aoa3.player.resource;

import net.minecraft.resources.ResourceLocation;
import com.deveisia.aoa3.common.registration.AoARegistries;
import com.deveisia.aoa3.player.AoAPlayerEventListener;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.annotation.Nonnull;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.common.util.Lazy;

public final class AoAResource {
	public ResourceLocation getRegistryName() {
		return AoARegistries.RESOURCES.get().getKey(this);
	}

	private final Lazy<MutableComponent> name;
	private final BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory;
	private final Function<CompoundTag, Instance> clientFactory;

	public AoAResource(BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory, Function<CompoundTag, Instance> clientFactory) {
		this.name = () -> Component.translatable(Util.makeDescriptionId("resource", getRegistryName()));
		this.jsonFactory = jsonFactory;
		this.clientFactory = clientFactory;
	}

	public MutableComponent getName() {
		return this.name.get();
	}

	public AoAResource.Instance buildDefaultInstance(ServerPlayerDataManager plData, JsonObject resourceData) {
		return jsonFactory.apply(plData, resourceData);
	}

	public AoAResource.Instance buildClientInstance(CompoundTag resourceData) {
		return clientFactory.apply(resourceData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoAResource resource;

		protected ServerPlayerDataManager playerDataManager;
		public boolean needsSync = true;

		protected Instance(AoAResource resource, ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
			this.resource = resource;
		}

		public void changePlayerInstance(ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
		}

		public abstract float getCurrentValue();
		public abstract void setValue(float amount);
		public abstract float getMaxValue();

		public float getPerTickRegen() {
			return 0;
		}

		public AoAResource type() {
			return this.resource;
		}

		public MutableComponent getName() {
			return type().getName();
		}

		public ServerPlayerDataManager getPlayerDataManager() {
			return this.playerDataManager;
		}

		public boolean hasAmount(float amount) {
			return getCurrentValue() >= amount;
		}

		public void addValue(float amount) {
			setValue(getCurrentValue() + amount);
		}

		public boolean consume(float amount, boolean consumeIfInsufficient) {
			if (!PlayerUtil.shouldPlayerBeAffected(playerDataManager.player()))
				return true;

			float current = getCurrentValue();

			if (current < amount && !consumeIfInsufficient) {
				PlayerUtil.notifyPlayerOfInsufficientResources(playerDataManager.player(), type(), amount);

				return false;
			}

			setValue(Math.max(0, current - amount));

			needsSync = true;

			return !consumeIfInsufficient || current >= amount;
		}

		@Nonnull
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}

		public void loadFromNbt(CompoundTag resourceDataNbt) {}

		public CompoundTag getSyncData(boolean forClientSetup) {
			return new CompoundTag();
		}

		public void receiveSyncData(CompoundTag data) {}
	}
}
