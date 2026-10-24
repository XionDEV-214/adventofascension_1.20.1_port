package com.deveisia.aoa3.event.custom;

import com.deveisia.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import com.deveisia.aoa3.event.custom.events.*;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public final class AoAEvents {
	public static void playerLevelChange(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, skill, oldLevel, wasNaturallyChanged));
	}

	public static float playerChangeXp(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, float xpGained, float xpAfterModifiers, boolean wasNaturallyGained) {
		PlayerChangeXpEvent event = new PlayerChangeXpEvent(playerDataManager, skill, xpGained, xpAfterModifiers, wasNaturallyGained);

		boolean cancelled = MinecraftForge.EVENT_BUS.post(event);

		return cancelled ? 0 : event.getNewXpGain();
	}

	public static HaulingItemFishedEvent haulingItemFished(Entity hookedEntity, ItemStack rodStack, List<ItemStack> lootList, int baseXp, int rodDamage, HaulingFishingBobberEntity bobber) {
		HaulingItemFishedEvent event = new HaulingItemFishedEvent(hookedEntity, rodStack, lootList, baseXp, rodDamage, bobber);

		MinecraftForge.EVENT_BUS.post(event);

		return event;
	}

	public static HaulingRodPullEntityEvent haulingRodPullEntity(Player player, ItemStack haulingRod, HaulingFishingBobberEntity bobber, Entity hookedEntity, int rodDamage, float pullStrength) {
		HaulingRodPullEntityEvent event = new HaulingRodPullEntityEvent(player, haulingRod, bobber, hookedEntity, rodDamage, pullStrength);

		MinecraftForge.EVENT_BUS.post(event);

		return event;
	}

	public static boolean firePlayerCraftingEvent(Player player, ItemStack crafting, CraftingContainer craftingInventory, ResultContainer outputInventory) {
		return MinecraftForge.EVENT_BUS.post(new ItemCraftingEvent(player, crafting, craftingInventory, outputInventory));
	}

	public static boolean firePlayerSmeltingEvent(Player player, ItemStack smelting, Container outputInventory) {
		return MinecraftForge.EVENT_BUS.post(new ItemSmeltingEvent(player, smelting, outputInventory));
	}
}
