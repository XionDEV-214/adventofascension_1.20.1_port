package com.deveisia.aoa3.content.block.functional.portal;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class NowherePortalBlock extends PortalBlock {
	public NowherePortalBlock() {
		super(AoADimensions.NOWHERE.key, MapColor.TERRACOTTA_GREEN, ColourUtil.RGB(255, 227, 117));
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity instanceof Player || (entity instanceof TamableAnimal && ((TamableAnimal)entity).isTame()))
			super.entityInside(state, world, pos, entity);
	}
}
