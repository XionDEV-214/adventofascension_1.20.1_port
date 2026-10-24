package com.deveisia.aoa3.content.world.teleporter.specific;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.content.block.functional.portal.PortalBlock;
import com.deveisia.aoa3.content.world.teleporter.AoATeleporter;
import com.deveisia.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class NowhereTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public BlockPos findExistingPortal(Level world, Entity entity) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			if (entity instanceof ServerPlayer) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);

				PortalCoordinatesContainer portalLoc = new PortalCoordinatesContainer(entity.level().dimension(), entity.getX(), entity.getY(), entity.getZ());

				plData.setPortalReturnLocation(world.dimension(), portalLoc);
			}

			return BlockPos.containing(0, 202, 0);
		}

		return super.findExistingPortal(world, entity);
	}

	@Override
	public BlockPos makePortal(Level world, Entity entity, BlockPos pos) {
		return pos;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.NOWHERE_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.ANCIENT_ROCK.get();
	}
}
