package com.deveisia.aoa3.content.block.functional.utility;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.content.block.tileentity.LunarCreationTableTileEntity;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.BlockUtil;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import com.deveisia.aoa3.mixin.common.accessor.CraftingMenuAccessor;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class LunarCreationTable extends Block implements EntityBlock {
	private static final Component CONTAINER_TITLE = Component.translatable("container." + AdventOfAscension.MOD_ID + ".lunar_creation_table");

	public LunarCreationTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MapColor.COLOR_PURPLE).stats(10f, 15f).tool(ToolType.PICKAXE).get());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new LunarCreationTableTileEntity(pos, state);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity tile = world.getBlockEntity(pos);

			if (tile instanceof LunarCreationTableTileEntity)
				((LunarCreationTableTileEntity)tile).dropContents(world, pos);

			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide()) {
			player.openMenu(state.getMenuProvider(world, pos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);

			return InteractionResult.CONSUME;
		}

		return InteractionResult.SUCCESS;
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, player) -> {
			CraftingMenu container = new CraftingMenu(id, inventory, ContainerLevelAccess.create(world, pos)) {
				@Override
				public void removed(Player player) {
					CraftingMenuAccessor accessor = (CraftingMenuAccessor)this;
					LunarCreationTableTileEntity tileEntity = accessor.getAccess().evaluate(this::getTileEntity, null);

					if (tileEntity != null) {
						tileEntity.setContents(accessor.getCraftSlots().getItems());

						accessor.getCraftSlots().clearContent();
					}

					super.removed(player);
				}

				@Nullable
				private LunarCreationTableTileEntity getTileEntity(Level world, BlockPos pos) {
					BlockEntity te = world.getBlockEntity(pos);

					return te instanceof LunarCreationTableTileEntity ? (LunarCreationTableTileEntity)te : null;
				}

				@Override
				public void slotsChanged(Container inventory) {
					CraftingMenuAccessor accessor = (CraftingMenuAccessor)this;
					LunarCreationTableTileEntity tileEntity = accessor.getAccess().evaluate(this::getTileEntity, null);

					if (tileEntity != null)
						tileEntity.setContents(accessor.getCraftSlots().getItems());

					super.slotsChanged(inventory);
				}

				@Override
				public boolean stillValid(Player player) {
					return stillValid(((CraftingMenuAccessor)this).getAccess(), player, AoABlocks.LUNAR_CREATION_TABLE.get());
				}
			};

			BlockEntity te = world.getBlockEntity(pos);

			if (te instanceof LunarCreationTableTileEntity) {
				NonNullList<ItemStack> cachedContents = ((LunarCreationTableTileEntity)te).getContents();

				CraftingContainer craftSlots = ((CraftingMenuAccessor)container).getCraftSlots();

				for (int i = 0; i < cachedContents.size(); i++) {
					craftSlots.setItem(i, cachedContents.get(i));
				}

				container.slotsChanged(craftSlots);
			}

			return container;
		}, CONTAINER_TITLE);
	}
}
