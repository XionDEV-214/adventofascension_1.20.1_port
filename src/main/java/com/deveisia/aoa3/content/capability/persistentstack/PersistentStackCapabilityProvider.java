package com.deveisia.aoa3.content.capability.persistentstack;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.Direction;
import net.minecraft.nbt.FloatTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PersistentStackCapabilityProvider implements ICapabilitySerializable<FloatTag> {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "persistent_stack");
	public static final Capability<PersistentStackCapabilityHandles> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

	private final PersistentStackCapability instance = new PersistentStackCapability();
	private final LazyOptional<PersistentStackCapability> implContainer = LazyOptional.of(() -> instance);

	public PersistentStackCapabilityProvider(@Nullable Direction dir) {}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public FloatTag serializeNBT() {
		return instance.serializeNBT();
	}

	@Override
	public void deserializeNBT(FloatTag nbt) {
		instance.deserializeNBT(nbt);
	}

	public static PersistentStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(INSTANCE, side).orElse(new PersistentStackCapability());
	}
}
