package com.deveisia.aoa3.event;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.common.registration.AoAArmour;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoATools;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.content.item.armour.AdventArmour;
import com.deveisia.aoa3.content.item.misc.ReservedItem;
import com.deveisia.aoa3.content.item.misc.summoning.BossSpawningItem;
import com.deveisia.aoa3.content.item.tool.misc.ExpFlask;
import com.deveisia.aoa3.content.item.weapon.sword.BaseSword;
import com.deveisia.aoa3.data.server.AoASkillReqReloadListener;
import com.deveisia.aoa3.event.custom.events.PlayerLevelChangeEvent;
import com.deveisia.aoa3.event.dimension.LelyetiaEvents;
import com.deveisia.aoa3.event.dimension.LunalusEvents;
import com.deveisia.aoa3.event.dimension.NowhereEvents;
import com.deveisia.aoa3.event.dimension.VoxPondsEvents;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.*;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class PlayerEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.PlayerTickEvent.class, PlayerEvents::onPlayerTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, PlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingAttackEvent.class, PlayerEvents::onPlayerHit);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingHurtEvent.class, PlayerEvents::onPlayerHurt);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, PlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDeathEvent.class, PlayerEvents::onEntityDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.BreakEvent.class, PlayerEvents::onBlockBreak);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.EntityPlaceEvent.class, PlayerEvents::onBlockPlace);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, PlayerEvents::onPlayerLogin);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTossEvent.class, PlayerEvents::onItemToss);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerXpEvent.PickupXp.class, PlayerEvents::onPlayerPickupXp);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, PlayerEvents::onPlayerFishing);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangedDimensionEvent.class, PlayerEvents::onDimensionChange);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerInteractEvent.RightClickBlock.class, PlayerEvents::onBlockInteract);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerLevelChangeEvent.class, PlayerEvents::onPlayerLevelUp);
	}

	private static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (WorldUtil.isWorld(ev.player.level(), AoADimensions.LELYETIA.key)) {
				LelyetiaEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level(), AoADimensions.VOX_PONDS.key)) {
				VoxPondsEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level(), AoADimensions.LUNALUS.key)) {
				LunalusEvents.doPlayerTick(ev.player);
			}
		}
		else {
			if (WorldUtil.isWorld(ev.player.level(), AoADimensions.NOWHERE.key))
				NowhereEvents.doPlayerTick(ev);
		}
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LUNALUS.key) && ev.getEntity() instanceof Player)
			LunalusEvents.doPlayerJump((Player)ev.getEntity());
	}

	private static void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer && ev.getEntity().getHealth() - ev.getAmount() <= 0 && ev.getEntity().level().getLevelData().isHardcore())
			ReservedItem.handlePlayerDeath((ServerPlayer)ev.getEntity());
	}

	private static void onPlayerHurt(final LivingHurtEvent ev) {
		Entity attacker = ev.getSource().getEntity();

		if (DamageUtil.isMeleeDamage(ev.getSource()) && attacker instanceof LivingEntity) {
			ItemStack weapon = ((LivingEntity)attacker).getItemInHand(InteractionHand.MAIN_HAND);

			if (weapon.getItem() instanceof BaseSword)
				ev.setAmount((float)((BaseSword)weapon.getItem()).getDamageForAttack(ev.getEntity(), (LivingEntity)attacker, weapon, ev.getAmount()));
		}

		if (ev.getEntity() instanceof ServerPlayer) {
			ServerPlayer pl = (ServerPlayer)ev.getEntity();

			if (pl.getHealth() > 0 && ev.getSource().is(DamageTypeTags.IS_EXPLOSION) && ev.getSource().getDirectEntity() instanceof Creeper) {
				if ((!pl.level().getEntitiesOfClass(PrimedTnt.class, ev.getSource().getDirectEntity().getBoundingBox().inflate(3)).isEmpty() || !pl.level().getEntitiesOfClass(PrimedTnt.class, pl.getBoundingBox().inflate(3)).isEmpty()) && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.CREEPONIA_REALMSTONE.get()));
			}
		}
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer) {
			ServerPlayer player = (ServerPlayer)ev.getEntity();

			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.LELYETIA_REALMSTONE.get()));

			if (WorldUtil.isWorld(player.level(), AoADimensions.LUNALUS.key))
				LunalusEvents.doPlayerLanding(player, ev);
		}
	}

	private static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().level().isClientSide) {
			if (ev.getEntity() instanceof ServerPlayer) {
				ReservedItem.handlePlayerDeath((ServerPlayer)ev.getEntity());
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayer) {
				if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.DEEPLANDS.key)) {
					if (ev.getEntity() instanceof FlyingMob)
						ev.getEntity().spawnAtLocation(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
			}
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		Player pl = ev.getPlayer();

		if (pl instanceof ServerPlayer) {
			BlockPos pos = ev.getPos();

			if (!pl.isCreative() && isOre(ev.getState()) && pos.getY() <= 5 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.DEEPLANDS_REALMSTONE.get()));
		}
	}

	private static boolean isOre(BlockState state) {
		if (state.is(Tags.Blocks.ORES))
			return true;

		ResourceLocation id = ForgeRegistries.BLOCKS.getKey(state.getBlock());

		return id != null && id.getPath().endsWith("_ore");
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer) {
			ServerPlayer pl = (ServerPlayer)ev.getEntity();

			if (PlayerUtil.isWearingFullSet(pl, AdventArmour.Type.HYDRANGIC)) {
				if (ev.getPlacedBlock().getBlock() instanceof BonemealableBlock && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getEntity().level(), ev.getPos(), pl)) {
					ev.getLevel().levelEvent(2005, ev.getPos(), 0);
					pl.getInventory().hurtArmor(pl.damageSources().generic(), 16, Inventory.ALL_ARMOR_SLOTS);
				}
			}
		}
	}

	private static void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		Level world = ev.getLevel();

		if (!world.isClientSide()) {
			BlockPos pos = ev.getPos();
			BlockState state = world.getBlockState(pos);

			if (state.getBlock() instanceof ComposterBlock && state.getValue(ComposterBlock.LEVEL) == 8 && RandomUtil.oneInNChance(5)) {
				ItemEntity item = new ItemEntity(world, (double)pos.getX() + RandomUtil.randomValueBetween(0.15, 0.85f), (double)pos.getY() + RandomUtil.randomValueBetween(0.66f, 1.36f), (double)pos.getZ() + RandomUtil.randomValueBetween(0.15, 0.85f), new ItemStack(AoAItems.GREEN_MANURE_SEEDS.get()));

				item.setDefaultPickUpDelay();
				world.addFreshEntity(item);
			}
		}
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer) {
			ServerPlayer pl = (ServerPlayer)ev.getEntity();
			UUID uuid = pl.getGameProfile().getId();
			String msg = null;

			if (uuid.compareTo(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4")) == 0) {
				msg = ChatFormatting.DARK_RED + "It begins...Is this the end?";

				((ServerLevel)pl.level()).sendParticles(ParticleTypes.LARGE_SMOKE, pl.getX(), pl.getY() + 0.2d, pl.getZ(), 16, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, 1);
			}
			else if (AoAHaloUtil.isCrazyDonator(uuid)) {
				msg = ChatFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				pl.getServer().getPlayerList().broadcastSystemMessage(Component.literal(msg), true);

			AoAHaloUtil.syncWithNewClient(pl);
			ServerPlayerDataManager.syncNewPlayer(pl);
			AoASkillReqReloadListener.syncNewPlayer(pl);

			PlayerAdvancements plAdvancements = pl.getAdvancements();
			Advancement rootAdv = AdvancementUtil.getAdvancement(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "overworld/root"));

			if (rootAdv == null) {
				Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things.");
			}
			else if (!plAdvancements.getOrStartProgress(rootAdv).isDone()) {
				plAdvancements.award(AdvancementUtil.getAdvancement(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "overworld/by_the_books")), "legitimate");
				plAdvancements.award(rootAdv, "playerjoin");
			}
		}
	}

	private static void onItemToss(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayer) {
			ItemEntity entityItem = ev.getEntity();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get()) {
				if (ev.getPlayer().isInLava()) {
					ItemUtil.givePlayerItemOrDrop(ev.getPlayer(), new ItemStack(AoAItems.NETHER_REALMSTONE.get()));
					ev.getEntity().discard();
				}
			}
			else if (item instanceof ReservedItem) {
				ReservedItem.handlePlayerToss(ev);
			}
			else if (item instanceof BossSpawningItem) {
				Level world = ev.getEntity().level();

				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					ev.getPlayer().sendSystemMessage(Component.translatable("message.feedback.spawnBoss.difficultyFail"));

					return;
				}

				ev.setCanceled(true);
				world.addFreshEntity(BossSpawningItem.newBossEntityItemFromExisting(entityItem, ev.getPlayer()));

				BossSpawningItem bossItem = (BossSpawningItem)item;

				if (bossItem.getThrowingSound() != null)
					world.playSound(null, entityItem.getX(), entityItem.getX(), entityItem.getZ(), bossItem.getThrowingSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
			}
		}
	}

	private static void onPlayerPickupXp(final PlayerXpEvent.PickupXp ev) {
		if (!ev.getEntity().level().isClientSide && ev.getOrb().value > 0) {
			ItemStack stack = ItemUtil.getStackFromInventory(ev.getEntity(), AoATools.EXP_FLASK.get());

			if (stack != null) {
				ExpFlask.addExp(stack, ev.getOrb().value);
				ev.setCanceled(true);
				ev.getOrb().value = 0;
				ev.getOrb().discard();
			}
		}
	}

	private static void onPlayerFishing(final ItemFishedEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LBOREAN.key) && RandomUtil.oneInNChance(10)) {
			FishingHook hook = ev.getHookEntity();
			LivingEntity fisher = ev.getEntity();

			ItemEntity drop = new ItemEntity(fisher.level(), hook.getX(), hook.getY(), hook.getZ(), new ItemStack(AoAItems.CALL_OF_THE_DRAKE.get()));
			double velocityX = fisher.getX() - hook.getX();
			double velocityY = fisher.getY() - hook.getY();
			double velocityZ = fisher.getZ() - hook.getZ();
			double velocity = (float)Math.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);

			drop.setDeltaMovement(velocityX * 0.1D, velocityY * 0.1D + (double)(float)Math.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
			fisher.level().addFreshEntity(drop);
		}
	}

	private static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getFrom() == AoADimensions.NOWHERE.key)
			NowhereEvents.doDimensionChange(ev);
	}

	private static void onPlayerLevelUp(final PlayerLevelChangeEvent ev) {
		if (!ev.getEntity().level().isClientSide() && ev.getOldLevel() < 100 && ev.getNewLevel() == 100) {
			AoASkill skill = ev.getSkill().type();

			if (skill == AoASkills.DEXTERITY.get()) {
				ItemUtil.givePlayerItemOrDrop(ev.getEntity(), new ItemStack(AoAArmour.HELM_OF_THE_DEXTROUS.get()));
			}
			else if (skill == AoASkills.INNERVATION.get()) {
				ItemUtil.givePlayerItemOrDrop(ev.getEntity(), new ItemStack(AoAArmour.HELM_OF_THE_WARRIOR.get()));
			}
			else if (skill == AoASkills.FARMING.get()) {
				ItemUtil.givePlayerItemOrDrop(ev.getEntity(), new ItemStack(AoAArmour.HELM_OF_THE_DRYAD.get()));
			}
			else if (skill == AoASkills.EXTRACTION.get()) {
				ItemUtil.givePlayerItemOrDrop(ev.getEntity(), new ItemStack(AoAArmour.HELM_OF_THE_TREASURER.get()));
			}
			else if (skill == AoASkills.HAULING.get()) {
				ItemUtil.givePlayerItemOrDrop(ev.getEntity(), new ItemStack(AoAArmour.HELM_OF_THE_TRAWLER.get()));
			}
		}
	}
}
