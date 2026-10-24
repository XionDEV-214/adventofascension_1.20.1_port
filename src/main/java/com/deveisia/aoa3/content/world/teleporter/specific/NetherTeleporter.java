package com.deveisia.aoa3.content.world.teleporter.specific;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.block.functional.portal.PortalBlock;
import com.deveisia.aoa3.content.world.teleporter.AoATeleporter;
import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetherTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.NETHER_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return Blocks.GLOWSTONE;
	}
}
