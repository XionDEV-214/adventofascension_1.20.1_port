package com.deveisia.aoa3.content.capability.adventplayer;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventPlayerCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "advent_player");
	public static final Capability<AdventPlayerCapabilityHandles> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

	private final AdventPlayerCapability instance;
	private final LazyOptional<AdventPlayerCapability> implContainer;

	public AdventPlayerCapabilityProvider(ServerPlayer player) {
		this.instance = new AdventPlayerCapability(player);
		this.implContainer = LazyOptional.of(() -> this.instance);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		return this.instance.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.instance.deserializeNBT(nbt);
	}
}
