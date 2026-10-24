package com.deveisia.aoa3.content.capability;

import com.deveisia.aoa3.content.capability.adventplayer.AdventPlayerCapabilityHandles;
import com.deveisia.aoa3.content.capability.adventplayer.AdventPlayerCapabilityProvider;
import com.deveisia.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import com.deveisia.aoa3.content.capability.volatilestack.VolatileStackCapabilityHandles;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public final class CapabilitiesManager {
	public static void init(IEventBus modBus) {
		modBus.addListener(CapabilitiesManager::registerCapabilities);
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, EventPriority.NORMAL, false, AttachCapabilitiesEvent.class, CapabilitiesManager::attachEntityCapabilities);
	}

	private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ServerPlayer player)
			event.addCapability(AdventPlayerCapabilityProvider.ID, new AdventPlayerCapabilityProvider(player));
	}

	private static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(VolatileStackCapabilityHandles.class);
		event.register(PersistentStackCapabilityHandles.class);
		event.register(AdventPlayerCapabilityHandles.class);
	}
}
