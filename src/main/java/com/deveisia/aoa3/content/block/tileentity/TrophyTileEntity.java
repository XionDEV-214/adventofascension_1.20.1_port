package com.deveisia.aoa3.content.block.tileentity;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoATileEntities;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TrophyTileEntity extends BlockEntity implements Nameable {
	@Nullable
	private Entity cachedEntity = null;
	@Nullable
	private String entityId = null;
	private boolean isOriginal = true;

	private Block trophyBlock = null;

	private float mobRotation;
	private float prevMobRotation;
	public double hoverStep;

	public TrophyTileEntity(BlockPos pos, BlockState state) {
		super(AoATileEntities.TROPHY.get(), pos, state);
	}

	public void setEntity(String entityId, boolean isEgg) {
		this.entityId = entityId;
		this.cachedEntity = null;
		this.isOriginal = !isEgg;
	}

	public void tick() {
		prevMobRotation = mobRotation;
		mobRotation = (mobRotation + 0.05f) % 360;

		if (trophyBlock == null && level != null)
			trophyBlock = level.getBlockState(getBlockPos()).getBlock();
	}

	public float getMobRotation() {
		return mobRotation;
	}

	public float getPrevMobRotation() {
		return prevMobRotation;
	}

	public String getEntityId() {
		return entityId;
	}

	public boolean isOriginal() {
		return isOriginal;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();

		if (entityId != null) {
			tag.putString("EntityID", entityId);
			tag.putBoolean("OriginalTrophy", isOriginal);
		}

		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);

		if (tag.contains("EntityID", Tag.TAG_STRING)) {
			entityId = tag.getString("EntityID");
			isOriginal = tag.getBoolean("OriginalTrophy");
		}
	}

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);

		if (entityId != null) {
			compound.putString("EntityID", entityId);
			compound.putBoolean("OriginalTrophy", isOriginal);
		}
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		if (compound.contains("EntityID")) {
			entityId = compound.getString("EntityID");
			isOriginal = compound.getBoolean("OriginalTrophy");
		}
	}

	@Nullable
	public Entity getCachedEntity() {
		if (this.cachedEntity == null && entityId != null) {
			CompoundTag entityNBT = new CompoundTag();

			entityNBT.putString("id", entityId);

			cachedEntity = EntityType.loadEntityRecursive(entityNBT, getLevel(), Function.identity());

			if (cachedEntity == null) {
				entityNBT = new CompoundTag();

				if (!"minecraft:player".equals(entityId))
					entityId = "minecraft:end_crystal";

				entityNBT.putString("id", entityId);

				cachedEntity = EntityType.loadEntityRecursive(entityNBT, getLevel(), Function.identity());
			}

			if (cachedEntity != null)
				cachedEntity.tickCount = 1;
		}

		return this.cachedEntity;
	}

	@Override
	public Component getName() {
		if (trophyBlock == null || entityId == null)
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy");

		if (getCachedEntity() == null)
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy");

		if (trophyBlock == AoABlocks.TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy.desc", cachedEntity.getName());

		if (trophyBlock == AoABlocks.GOLD_TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.gold_trophy.desc", cachedEntity.getName());

		if (trophyBlock == AoABlocks.ORNATE_TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.ornate_trophy.desc", cachedEntity.getName());

		return LocaleUtil.getLocaleMessage("block.aoa3.trophy");
	}

	@Override
	public boolean hasCustomName() {
		return trophyBlock != null && entityId != null;
	}

	@Nullable
	@Override
	public Component getCustomName() {
		return getName();
	}
}
