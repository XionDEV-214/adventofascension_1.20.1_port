package com.deveisia.aoa3.common.registration.custom;

import com.deveisia.aoa3.advent.AdventOfAscension;
import java.util.function.Supplier;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.player.resource.EnergyResource;
import com.deveisia.aoa3.player.resource.RageResource;
import com.deveisia.aoa3.player.resource.SpiritResource;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AoAResources {
	public static final ResourceKey<Registry<AoAResource>> REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("resources"));
	public static final DeferredRegister<AoAResource> RESOURCES = DeferredRegister.create(REGISTRY_KEY, AdventOfAscension.MOD_ID);
	public static final Supplier<IForgeRegistry<AoAResource>> REGISTRY = RESOURCES.makeRegistry(() -> new RegistryBuilder<AoAResource>().disableSaving());

	public static final AoAResource.Instance DEFAULT = new AoAResource.Instance(null, null) {
		@Override
		public float getCurrentValue() {
			return 0;
		}

		@Override
		public boolean hasAmount(float amount) {
			return false;
		}

		@Override
		public void setValue(float amount) {}

		@Override
		public float getMaxValue() {
			return 0;
		}

		@Nonnull
		@Override
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}
	};

	public static final RegistryObject<AoAResource> SPIRIT = RESOURCES.register("spirit", () -> new AoAResource(SpiritResource::new, SpiritResource::new));
	public static final RegistryObject<AoAResource> ENERGY = RESOURCES.register("energy", () -> new AoAResource(EnergyResource::new, EnergyResource::new));
	public static final RegistryObject<AoAResource> RAGE = RESOURCES.register("rage", () -> new AoAResource(RageResource::new, RageResource::new));

	@Nullable
	public static AoAResource getResource(ResourceLocation id) {
		return getRegistry().getValue(id);
	}

	public static IForgeRegistry<AoAResource> getRegistry() {
		return REGISTRY.get();
	}
}
