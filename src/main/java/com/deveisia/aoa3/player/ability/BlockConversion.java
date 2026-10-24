package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.RegistryUtil;
import com.deveisia.aoa3.util.WorldUtil;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockConversion extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	private final int radius;
	private final Block targetBlock;
	private final Block replacementBlock;
	@Nullable
	private final Item interactionItem;

	public BlockConversion(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BLOCK_CONVERSION.get(), skill, data);

		this.radius = GsonHelper.getAsInt(data, "radius", 1);
		this.targetBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "target_block")));
		this.replacementBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "replacement_block")));

		if (data.has("interaction_item")) {
			this.interactionItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "interaction_item")));
		}
		else {
			this.interactionItem = null;
		}

		if (this.radius < 0)
			throw new IllegalArgumentException("Invalid radius value for BlockConversion ability: '" + this.radius + "'. Must be at least 0");
	}

	public BlockConversion(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BLOCK_CONVERSION.get(), skill, data);

		this.radius = data.getInt("radius");
		this.targetBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(data.getString("target_block")));
		this.replacementBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(data.getString("replacement_block")));

		if (data.contains("interaction_item")) {
			this.interactionItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(data.getString("interaction_item")));
		}
		else {
			this.interactionItem = null;
		}
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		MutableComponent description;
		String suffix = radius > 0 ? "" : ".single";

		if (this.interactionItem != null) {
			description = Component.translatable(LocaleUtil.getKey(defaultDescription) + suffix, this.radius, this.targetBlock.getName(), this.replacementBlock.getName(), this.interactionItem.getName(this.interactionItem.getDefaultInstance()));
		}
		else {
			description = Component.translatable(LocaleUtil.getKey(defaultDescription) + ".noItem" + suffix, this.radius, this.targetBlock.getName(), this.replacementBlock.getName());
		}

		super.updateDescription(description);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getLevel().getBlockState(ev.getPos()).getBlock() == this.targetBlock) {
			ItemStack heldStack = ev.getEntity().getItemInHand(ev.getHand());

			if (this.interactionItem == null || heldStack.getItem() == this.interactionItem) {
				Level world = ev.getLevel();
				BlockPos pos = ev.getPos();
				BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
				BlockState newState = replacementBlock.defaultBlockState();
				boolean success = false;

				switch (ev.getHitVec().getDirection()) {
					case EAST:
					case WEST:
						for (int y = -radius; y <= radius; y++) {
							for (int z = -radius; z <= radius; z++) {
								mutablePos.set(pos.getX(), pos.getY() + y, pos.getZ() + z);

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getEntity(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
					case NORTH:
					case SOUTH:
						for (int y = -radius; y <= radius; y++) {
							for (int x = -radius; x <= radius; x++) {
								mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ());

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getEntity(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
					case UP:
					case DOWN:
						for (int x = -radius; x <= radius; x++) {
							for (int z = -radius; z <= radius; z++) {
								mutablePos.set(pos.getX() + x, pos.getY(), pos.getZ() + z);

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getEntity(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
				}

				if (success && this.interactionItem != null && !ev.getEntity().isCreative())
					heldStack.shrink(1);
			}
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("radius", this.radius);
			data.putString("target_block", RegistryUtil.getId(this.targetBlock).toString());
			data.putString("replacement_block", RegistryUtil.getId(this.replacementBlock).toString());

			if (this.interactionItem != null)
				data.putString("interaction_item", RegistryUtil.getId(this.interactionItem).toString());
		}

		return data;
	}
}
