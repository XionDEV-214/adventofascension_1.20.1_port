package com.deveisia.aoa3.content.block.tileentity;

import com.deveisia.aoa3.common.registration.AoATileEntities;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LunarCreationTableTileEntity extends BlockEntity {
	private final NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

	public LunarCreationTableTileEntity(BlockPos pos, BlockState state) {
		super(AoATileEntities.LUNAR_CREATION_TABLE.get(), pos, state);
	}

	public NonNullList<ItemStack> getContents() {
		return this.items;
	}

	public void dropContents(Level world, BlockPos pos) {
		for (ItemStack stack : getContents()) {
			world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, stack));
		}

		this.items.clear();
		setChanged();
	}

	public void setContents(int index, ItemStack item) {
		this.items.set(index, item);

		setChanged();

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
	}

	public void setContents(List<ItemStack> contents) {
		this.items.clear();

		for (int i = 0; i < 9 && contents.size() > i; i++) {
			this.items.set(i, contents.get(i));
		}

		setChanged();

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
	}

	private CompoundTag serializeContents() {
		CompoundTag tag = new CompoundTag();

		for (int i = 0; i < 9; i++) {
			if (!items.get(i).isEmpty())
				tag.put(String.valueOf(i), items.get(i).serializeNBT());
		}

		return tag;
	}

	private void deserializeContents(CompoundTag contentsTag) {
		items.clear();

		for (int i = 0; i < 9; i++) {
			if (contentsTag.contains(String.valueOf(i)))
				items.set(i, ItemStack.of(contentsTag.getCompound(String.valueOf(i))));
		}
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();

		tag.put("contents", serializeContents());

		return tag;
	}

	@Override
	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);

		compound.put("contents", serializeContents());
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		deserializeContents(compound.getCompound("contents"));
	}

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		if (pkt.getTag() != null)
			deserializeContents(pkt.getTag().getCompound("contents"));
	}
}
