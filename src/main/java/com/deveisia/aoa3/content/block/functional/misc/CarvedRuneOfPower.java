package com.deveisia.aoa3.content.block.functional.misc;

import com.deveisia.aoa3.content.block.functional.portal.PortalBlock;
import com.deveisia.aoa3.content.item.misc.BlankRealmstone;
import com.deveisia.aoa3.content.item.misc.Realmstone;
import com.deveisia.aoa3.content.world.teleporter.AoAPortalFrame;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class CarvedRuneOfPower extends Block {
	public CarvedRuneOfPower() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_BLACK).stats(3f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide) {
			if (player.getItemInHand(hand).getItem() instanceof Realmstone) {
				Realmstone realmstone = (Realmstone)player.getItemInHand(hand).getItem();
				PortalBlock portalBlock = (PortalBlock)realmstone.getPortalBlock().get();
				AoAPortalFrame.PortalDirection direction = AoAPortalFrame.testFrameForActivation(world, pos, hit.getDirection(), portalBlock);

				if (direction == AoAPortalFrame.PortalDirection.EXISTING) {
					player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.existing"));
				}
				else if (direction == AoAPortalFrame.PortalDirection.INVALID) {
					player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.fail"));
				}
				else {
					AoAPortalFrame.lightPortalFrame(world, pos, direction, portalBlock);

					if (realmstone.getActivationSound() != null)
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), realmstone.getActivationSound().get(), SoundSource.MASTER, 1.0f, 1.0f);
				}

				return InteractionResult.SUCCESS;
			}
			else if (player.getItemInHand(hand).getItem() instanceof BlankRealmstone) {
				if (world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof PortalBlock) {
					world.setBlockAndUpdate(pos.relative(Direction.UP), Blocks.AIR.defaultBlockState());

					return InteractionResult.SUCCESS;
				}

				return InteractionResult.FAIL;
			}
			else {
				return InteractionResult.FAIL;
			}
		}

		return InteractionResult.SUCCESS;
	}
}
