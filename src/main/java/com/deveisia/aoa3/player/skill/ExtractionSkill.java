package com.deveisia.aoa3.player.skill;

import com.deveisia.aoa3.common.registration.AoATags;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExtractionSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION, ListenerType.ITEM_SMELTED};

	public ExtractionSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.EXTRACTION.get(), plData, jsonData);
	}

	public ExtractionSkill(CompoundTag nbtData) {
		super(AoASkills.EXTRACTION.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!canGainXp(true))
			return;

		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);

		if (origin == null)
			return;

		ServerLevel world = context.getLevel();
		BlockPos pos = BlockPos.containing(origin);
		Block block = state.getBlock();

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), pos)))
			return;

		if (!isApplicableBlock(block))
			return;

		float hardness = state.getDestroySpeed(world, pos);
		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 2 * hardness);

		for (ItemStack item : loot) {
			if (item.getItem() != block.asItem()) {
				xp *= 2f;

				break;
			}
		}

		if (loot.size() > 2)
			xp *= 1.5f;

		adjustXp(xp, false, false);
	}

	@Override
	public void handleItemSmelted(PlayerEvent.ItemSmeltedEvent ev) {
		if (ev.getEntity().level().isClientSide())
			return;

		Item item = ev.getSmelting().getItem();

		if (item.getFoodProperties() == null && canGainXp(true)) {
			float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 40);

			if (ev.getSmelting().is(Tags.Items.NUGGETS)) {
				xp *= 1.5f;
			}
			else if (ev.getSmelting().is(Tags.Items.INGOTS)) {
				xp *= 2f;
			}
			else if (ev.getSmelting().is(Tags.Items.GEMS)) {
				xp *= 2.5f;
			}

			adjustXp(xp, false, false);
		}
	}

	public static boolean isApplicableBlock(Block block) {
		return block.defaultBlockState().is(Tags.Blocks.STONE) ||
				block.defaultBlockState().is(Tags.Blocks.COBBLESTONE) ||
				block.defaultBlockState().is(Tags.Blocks.ORES) ||
				block.defaultBlockState().is(BlockTags.LOGS) ||
				block.defaultBlockState().is(Tags.Blocks.SAND) ||
				block.defaultBlockState().is(AoATags.Blocks.GRASS) ||
				block.defaultBlockState().is(BlockTags.DIRT) ||
				block.defaultBlockState().is(Tags.Blocks.GRAVEL) ||
				block.defaultBlockState().is(Tags.Blocks.NETHERRACK) ||
				block.defaultBlockState().is(Tags.Blocks.OBSIDIAN);
	}
}
