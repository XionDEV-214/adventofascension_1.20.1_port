package com.deveisia.aoa3.content.world.teleporter.specific;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.block.functional.portal.PortalBlock;
import com.deveisia.aoa3.content.world.teleporter.AoATeleporter;
import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

public class LBoreanTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.LBOREAN_PORTAL.get();
	}

	@Override
	public BlockPos makePortal(Level world, Entity entity, BlockPos pos) {
		BlockPos returnPos = super.makePortal(world, entity, pos);
		BlockState border = getBorderBlock().defaultBlockState();
		BlockState air = Blocks.AIR.defaultBlockState();
		Vec3 centerPos = Vec3.atBottomCenterOf(pos.above(2));

		for (int x = -4; x <= 4; x++) {
			for (int y = -2; y <= 6; y++) {
				for (int z = -4; z <= 4; z++) {
					BlockPos placePos = pos.offset(x, y, z);
					double dist = Vec3.atCenterOf(placePos).distanceToSqr(centerPos);

					if (dist <= 16 && world.isInWorldBounds(placePos)) {
						BlockState state = world.getBlockState(placePos);

						if (state.getBlock() instanceof PortalBlock || state.is(border.getBlock()))
							continue;

						if (dist <= 9) {
							if (!state.getFluidState().isEmpty() || state.isAir())
								world.setBlock(placePos, air, 2);
						}
						else if (!state.getFluidState().isEmpty() || state.isAir()) {
							world.setBlock(placePos, border, 2);
						}
					}
				}
			}
		}

		return returnPos;
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.PINK_CORAL.get();
	}
}
