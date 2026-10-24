package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.ItemUtil;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.Tags;

public class BonusMiningResult extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public BonusMiningResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	public BonusMiningResult(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		if (!testAsChance())
			return;

		Block block = state.getBlock();

		if (!state.is(Tags.Blocks.ORES))
			return;

		Item blockItem = block.asItem();

		if (blockItem != Items.AIR) {
			for (ItemStack stack : loot) {
				if (stack.getItem() == blockItem)
					return;
			}
		}

		List<ItemStack> extraStacks = new ArrayList<ItemStack>();

		for (ItemStack stack : loot) {
			extraStacks.addAll(ItemUtil.increaseStackSize(stack, 1));
		}

		loot.addAll(extraStacks);
	}
}
