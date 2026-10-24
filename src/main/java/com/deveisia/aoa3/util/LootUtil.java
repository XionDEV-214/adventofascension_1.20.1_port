package com.deveisia.aoa3.util;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public final class LootUtil {
	public static LootTable getTable(ServerLevel world, ResourceLocation table) {
		return world.getServer().getLootData().getLootTable(table);
	}

	@Nonnull
	public static List<ItemStack> generateLoot(ServerLevel world, ResourceLocation table, LootParams context) {
		LootTable lootTable = getTable(world, table);

		if (lootTable == LootTable.EMPTY)
			return Lists.<ItemStack>newArrayList();

		return lootTable.getRandomItems(context);
	}

	public static LootParams getGiftContext(ServerLevel world, Vec3 position, Entity targetEntity) {
		return getGiftContext(world, position, 0, targetEntity);
	}

	public static LootParams getGiftContext(ServerLevel world, Vec3 position, float luck, Entity targetEntity) {
		return new LootParams.Builder(world).withParameter(LootContextParams.THIS_ENTITY, targetEntity).withParameter(LootContextParams.ORIGIN, position).withLuck(luck).create(LootContextParamSets.GIFT);
	}
}
