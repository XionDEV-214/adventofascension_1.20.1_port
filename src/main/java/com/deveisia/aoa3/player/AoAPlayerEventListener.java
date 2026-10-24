package com.deveisia.aoa3.player;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.ScreenOverlayPacket;
import com.deveisia.aoa3.event.custom.events.*;
import java.util.List;
import net.minecraft.client.KeyMapping;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;

@SuppressWarnings("unused")
public interface AoAPlayerEventListener {

	default ListenerType[] getListenerTypes() {
		return new ListenerType[] {};
	}

	default ListenerState getListenerState() {
		return ListenerState.ACTIVE;
	}

	default boolean meetsRequirements() {
		return true;
	}

	default void reenable(boolean isInit) {}

	default void disable(ListenerState reason, boolean isInit) {}

	default void handlePlayerTick(final TickEvent.PlayerTickEvent ev) {}

	default void handlePlayerJump(final LivingEvent.LivingJumpEvent ev) {}

	default void handlePlayerFall(final LivingFallEvent ev) {}

	default void handlePlayerDeath(final LivingDeathEvent ev) {}

	default void handlePlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {}

	default void handlePlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {}

	default void handlePlayerLogout(final PlayerEvent.PlayerLoggedOutEvent ev) {}

	default void handlePlayerDataClone(final PlayerEvent.Clone ev) {}

	default void handleArmourChange(final LivingEquipmentChangeEvent ev) {}

	default void handleDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {}

	default void handleGamemodeChange(final PlayerEvent.PlayerChangeGameModeEvent ev) {}

	default void handleBlockHarvestAttempt(final PlayerEvent.HarvestCheck ev) {}

	default void handleHarvestSpeedCheck(final PlayerEvent.BreakSpeed ev) {}

	default void handleBlockBreak(final BlockEvent.BreakEvent ev) {}

	default void handleBlockPlacement(final BlockEvent.EntityPlaceEvent ev) {}

	default void handleBlockInteraction(final PlayerInteractEvent.RightClickBlock ev) {}

	default void handleItemToss(final ItemTossEvent ev) {}

	default void handleLevelChange(final PlayerLevelChangeEvent ev) {}

	default void handleSkillXpGain(final PlayerChangeXpEvent ev) {}

	default void handleVanillaXpGain(final PlayerXpEvent.XpChange ev) {}

	default void handleItemCrafting(final ItemCraftingEvent ev) {}

	default void handleItemCrafted(final PlayerEvent.ItemCraftedEvent ev) {}

	default void handleItemSmelting(final ItemSmeltingEvent ev) {}

	default void handleItemSmelted(final PlayerEvent.ItemSmeltedEvent ev) {}

	default void handleItemFished(final ItemFishedEvent ev, boolean isHauling) {}

	default void handleHaulingRodPullEntity(final HaulingRodPullEntityEvent ev) {}

	default void handleAppliedPotion(final MobEffectEvent.Added ev) {}

	default void applyAttributeModifiers(final ServerPlayerDataManager plData) {}

	default void removeAttributeModifiers(final ServerPlayerDataManager plData) {}

	default void handleCriticalHit(final CriticalHitEvent ev) {}

	default void handleEntityTarget(final LivingChangeTargetEvent ev) {}

	default void handlePreIncomingAttack(final LivingAttackEvent ev) {}

	default void handlePreOutgoingAttack(final LivingAttackEvent ev) {}

	default void handleIncomingAttack(final LivingHurtEvent ev) {}

	default void handleOutgoingAttack(final LivingHurtEvent ev) {}

	default void handlePostIncomingAttack(final LivingDamageEvent ev) {}

	default void handlePostOutgoingAttack(final LivingDamageEvent ev) {}

	default void handleEntityKill(final LivingDeathEvent ev) {}

	default void handleAnimalBreed(final BabyEntitySpawnEvent ev) {}

	default void handleLootModification(final List<ItemStack> loot, LootContext context) {}

	@OnlyIn(Dist.CLIENT)
	default KeyMapping getKeybind() {
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	default boolean shouldSendKeyPress() {
		return true;
	}

	default void handleKeyInput() {}

	default void activatedActionKey(ServerPlayer player) {
		AoAPackets.messagePlayer(player, new ScreenOverlayPacket(AdventOfAscension.id("textures/gui/overlay/misc/action_key_activation_vignette.png"), 10));
	}

	default void handleCustomInteraction(String interactionType, Object data) {}

	enum ListenerState {
		ACTIVE("active"),
		MANUALLY_DISABLED("disabled"),
		DEACTIVATED("deactivated");

		private final String id;

		ListenerState(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public static ListenerState fromId(String id) {
			switch (id) {
				case "disabled":
					return MANUALLY_DISABLED;
				case "deactivated":
					return DEACTIVATED;
				case "active":
				default:
					return ACTIVE;
			}
		}
	}

	enum ListenerType {
		PLAYER_TICK,
		PLAYER_JUMP,
		PLAYER_FALL,
		PLAYER_DEATH,
		PLAYER_RESPAWN,
		PLAYER_LOGIN,
		PLAYER_LOGOUT,
		PLAYER_CLONE,
		EQUIPMENT_CHANGE,
		DIMENSION_CHANGE,
		GAMEMODE_CHANGE,
		BLOCK_HARVEST_ATTEMPT,
		BLOCK_BREAK_SPEED,
		BLOCK_BREAK,
		BLOCK_PLACE,
		BLOCK_INTERACT,
		ITEM_THROW,
		LEVEL_CHANGE,
		GAIN_SKILL_XP,
		GAIN_VANILLA_XP,
		ITEM_CRAFTING,
		ITEM_CRAFTED,
		ITEM_SMELTING,
		ITEM_SMELTED,
		FISHED_ITEM,
		HAULING_ROD_PULL_ENTITY,
		POTION_APPLIED,
		LOOT_MODIFICATION,
		ATTRIBUTE_MODIFIERS,
		CRITICAL_HIT,
		ENTITY_TARGET,
		INCOMING_ATTACK_BEFORE,
		INCOMING_ATTACK_DURING,
		INCOMING_ATTACK_AFTER,
		OUTGOING_ATTACK_BEFORE,
		OUTGOING_ATTACK_DURING,
		OUTGOING_ATTACK_AFTER,
		ENTITY_KILL,
		ANIMAL_BREED,
		KEY_INPUT,
		CUSTOM
	}
}
