package com.deveisia.aoa3.client.event;

import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import com.deveisia.aoa3.content.item.armour.NightVisionGoggles;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import com.deveisia.aoa3.mixin.client.accessor.SoundManagerAccessor;
import com.deveisia.aoa3.mixin.client.accessor.SoundEngineAccessor;
import com.deveisia.aoa3.client.AoAKeybinds;
import com.deveisia.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.HaloChangePacket;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.content.entity.mob.greckon.SilencerEntity;
import com.deveisia.aoa3.data.server.AoASkillReqReloadListener;
import com.deveisia.aoa3.event.GlobalEvents;
import com.deveisia.aoa3.player.ClientPlayerDataManager;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.scheduling.AoAScheduler;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.NetworkDirection;
import org.lwjgl.glfw.GLFW;

public final class ClientEventHandler {
	public static void init() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ClientTickEvent.class, ClientEventHandler::onClientTick);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingIn.class, ClientEventHandler::onPlayerJoin);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingOut.class, ClientEventHandler::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, ClientEventHandler::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, PlaySoundEvent.class, ClientEventHandler::onSoundPlay);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, ClientEventHandler::onTooltip);
		forgeBus.addListener(EventPriority.NORMAL, false, RenderGuiOverlayEvent.Pre.class, ClientEventHandler::onRenderGuiOverlay);
	}

	private static void onRenderGuiOverlay(final RenderGuiOverlayEvent.Pre ev) {
		if (!ev.getOverlay().id().equals(VanillaGuiOverlay.HELMET.id()))
			return;

		Minecraft mc = Minecraft.getInstance();

		if (mc.player == null || !mc.options.getCameraType().isFirstPerson())
			return;

		ItemStack helmet = mc.player.getItemBySlot(EquipmentSlot.HEAD);

		if (helmet.getItem() instanceof NightVisionGoggles)
			((NightVisionGoggles)helmet.getItem()).renderHelmetOverlay(helmet, mc.player, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), ev.getPartialTick());
	}

	private static void onClientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (!Minecraft.getInstance().isPaused())
				ScreenOverlayRenderer.tickOverlays();

			if (!Minecraft.getInstance().hasSingleplayerServer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	private static void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn ev) {
		{
			if (AoAConfig.CLIENT.showWelcomeMessage.get()) {
				if (AoAKeybinds.ADVENT_GUI.getKey().getValue() == GLFW.GLFW_KEY_UNKNOWN) {
					ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", ChatFormatting.GRAY));
				}
				else {
					ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage("message.login.welcome", ChatFormatting.GRAY, AoAKeybinds.ADVENT_GUI.getTranslatedKeyMessage()));
				}
			}

			AoAPackets.messageServer(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()));
			ClientPlayerDataManager.get().updatePlayerInstance(ev.getPlayer());
		}
	}

	private static void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut ev) {
		ClientPlayerDataManager.get().reset();
	}

	private static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() == Minecraft.getInstance().player)
			ScreenOverlayRenderer.clearOverlays();
	}

	private static void onSoundPlay(PlaySoundEvent ev) {
		if (SilencerEntity.isClientNearby) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (player == null) {
				SilencerEntity.isClientNearby = false;

				return;
			}

			if (player.level().getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				((SoundEngineAccessor)((SoundManagerAccessor)Minecraft.getInstance().getSoundManager()).getSoundEngine()).getListener().setGain(SilencerEntity.previousGain);
			}
		}
	}

	private static void onTooltip(final ItemTooltipEvent ev) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(RegistryUtil.getId(ev.getItemStack().getItem()));

		if (!restrictions.isEmpty()) {
			List<Component> lines = ev.getToolTip();

			if (ev.getFlags().isAdvanced()) {
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq", ChatFormatting.DARK_RED));

				int index = 2;

				for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> reqEntry : restrictions.entrySet()) {
					lines.add(index++, Component.literal("  ").withStyle(ChatFormatting.RED).append(LocaleUtil.getLocaleMessage(Util.makeDescriptionId("ability", AoAAbilities.LEVEL_RESTRICTION.getId()) + ".description." + reqEntry.getKey())).append(":"));

					for (Pair<ResourceLocation, Integer> pair : reqEntry.getValue()) {
						AoASkill skill = AoASkills.getSkill(pair.getFirst());

						lines.add(index++, Component.literal("    ").withStyle(ChatFormatting.GOLD).append(pair.getSecond() + " ").append(skill.getName()));
					}
				}

				lines.add(index, Component.literal(""));
			}
			else {
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq.hidden", ChatFormatting.DARK_RED));
			}
		}
	}
}
