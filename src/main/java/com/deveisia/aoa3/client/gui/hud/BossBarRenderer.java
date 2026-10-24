package com.deveisia.aoa3.client.gui.hud;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.HashMap;

public final class BossBarRenderer {
	private static final HashMap<String, ResourceLocation> textureCache = new HashMap<String, ResourceLocation>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, CustomizeGuiOverlayEvent.BossEventProgress.class, BossBarRenderer::onBossInfoRender);
	}

	private static void onBossInfoRender(final CustomizeGuiOverlayEvent.BossEventProgress ev) {
		if (ev.isCanceled())
			return;

		Component nameComponent = ev.getBossEvent().getName();

		if (nameComponent.getSiblings().isEmpty() || !(nameComponent.getContents() instanceof TranslatableContents contents))
			return;

		String id = contents.getKey();

		if (!id.startsWith("entity.aoa3."))
			return;

		Component name = nameComponent.getSiblings().get(0);
		Minecraft mc = Minecraft.getInstance();
		GuiGraphics guiGraphics = ev.getGuiGraphics();
		Window mainWindow = mc.getWindow();
		ResourceLocation texture = getTexture(id.substring(12));
		int textureWidth = 196;
		int xPos = mainWindow.getGuiScaledWidth() / 2 - 100;
		int percentPixels = (int)Math.ceil(ev.getBossEvent().getProgress() * textureWidth);
		int stringWidth = mc.font.width(name);
		int x = mainWindow.getGuiScaledWidth() / 2 - stringWidth / 2;

		if (percentPixels < textureWidth)
			guiGraphics.blit(texture, xPos, ev.getY(), 0, 12, 200, 12, 200, 36);

		if (percentPixels > 0)
			guiGraphics.blit(texture, xPos + 2, ev.getY(), 2, 0, percentPixels, 12, 200, 36);

		guiGraphics.blit(texture, xPos, ev.getY(), 0, 24, 200, 12, 200, 36);
		guiGraphics.drawString(mc.font, name, x, ev.getY() - 9, 16777215, true);

		ev.setIncrement(ev.getIncrement() + 5);
		ev.setCanceled(true);
	}

	private static ResourceLocation getTexture(String id) {
		return textureCache.computeIfAbsent(id, key -> ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/bossbars/" + key + ".png"));
	}
}
