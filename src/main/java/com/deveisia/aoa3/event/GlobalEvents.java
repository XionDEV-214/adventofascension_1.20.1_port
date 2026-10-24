package com.deveisia.aoa3.event;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.content.world.spawner.TraderSpawner;
import com.deveisia.aoa3.content.world.spawner.VisualentSpawner;
import com.deveisia.aoa3.scheduling.AoAScheduler;
import com.deveisia.aoa3.util.WebUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import com.deveisia.aoa3.mixin.common.accessor.ServerWorldAccessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public final class GlobalEvents {
	public static int tick;

	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ServerTickEvent.class, GlobalEvents::serverTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LevelEvent.Load.class, GlobalEvents::worldLoad);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStartingEvent.class, GlobalEvents::serverStarting);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStoppingEvent.class, GlobalEvents::serverStopping);
	}

	private static void serverTick(final TickEvent.ServerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			AoAScheduler.handleSyncScheduledTasks(tick);
		}
	}

	private static void worldLoad(final LevelEvent.Load ev) {
		if (ev.getLevel() instanceof ServerLevel) {
			ServerLevel world = (ServerLevel)ev.getLevel();
			ArrayList<CustomSpawner> spawners = new ArrayList<CustomSpawner>(((ServerWorldAccessor)world).getCustomSpawners());

			if (world.dimension() == AoADimensions.LUNALUS.key)
				spawners.add(new VisualentSpawner());

			if (TraderSpawner.isValidSpawnWorld(world))
				spawners.add(new TraderSpawner());

			((ServerWorldAccessor)world).setCustomSpawners(ImmutableList.copyOf(spawners));
		}
	}

	private static void serverStarting(final ServerStartingEvent ev) {
		WebUtil.extraPlayerHalosFromWeb();
		AoAScheduler.serverStartupTasks();
	}

	private static void serverStopping(final ServerStoppingEvent ev) {
		if (ev.getServer().isDedicatedServer())
			AoAScheduler.serverShutdownTasks();
	}
}
