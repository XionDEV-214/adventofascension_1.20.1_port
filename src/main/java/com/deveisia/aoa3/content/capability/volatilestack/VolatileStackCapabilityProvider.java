package com.deveisia.aoa3.content.capability.volatilestack;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VolatileStackCapabilityProvider implements ICapabilityProvider {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "volatile_stack");
	public static final Capability<VolatileStackCapabilityHandles> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

	private final LazyOptional<VolatileStackCapability> implContainer = LazyOptional.of(VolatileStackCapability::new);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	public static VolatileStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(INSTANCE, side).orElse(new VolatileStackCapability());
	}
}
