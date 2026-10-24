package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.player.skill.ExtractionSkill;
import com.deveisia.aoa3.util.LootUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class RareTableHarvestingChance extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public RareTableHarvestingChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.RARE_TABLE_HARVESTING_CHANCE.get(), skill, data);
	}

	public RareTableHarvestingChance(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.RARE_TABLE_HARVESTING_CHANCE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!testAsChance())
			return;

		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);

		if (origin == null)
			return;

		ServerLevel world = context.getLevel();
		Block block = state.getBlock();

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), BlockPos.containing(origin))))
			return;

		if (!ExtractionSkill.isApplicableBlock(block))
			return;

		Player player = getPlayer();

		loot.addAll(LootUtil.generateLoot(world,  ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "misc/lotto_totem"), LootUtil.getGiftContext(world, origin, player.getLuck(), player)));
		PlayerUtil.giveXpToPlayer((ServerPlayer)player, AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(player, AoASkills.FARMING.get()), 10), false);
	}
}
