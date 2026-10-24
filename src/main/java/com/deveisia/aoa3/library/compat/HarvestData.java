package com.deveisia.aoa3.library.compat;

import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public final class HarvestData {
	private static final Map<BlockBehaviour.Properties, ToolType> TOOLS = Collections.synchronizedMap(new IdentityHashMap<>());
	private static final Map<BlockBehaviour.Properties, Integer> LEVELS = Collections.synchronizedMap(new IdentityHashMap<>());

	public static void setTool(BlockBehaviour.Properties properties, ToolType tool) {
		TOOLS.put(properties, tool);
	}

	public static void setLevel(BlockBehaviour.Properties properties, int level) {
		LEVELS.put(properties, level);
	}

	public static ToolType getTool(BlockBehaviour.Properties properties) {
		return TOOLS.get(properties);
	}

	public static int getLevel(BlockBehaviour.Properties properties) {
		return LEVELS.getOrDefault(properties, 0);
	}

	public static void copy(BlockBehaviour.Properties from, BlockBehaviour.Properties to) {
		ToolType tool = TOOLS.get(from);

		if (tool != null)
			TOOLS.put(to, tool);

		Integer level = LEVELS.get(from);

		if (level != null)
			LEVELS.put(to, level);

		Material material = Material.ofProperties(from);

		if (material != null)
			Material.track(to, material);
	}
}
