package com.deveisia.aoa3.client.gui.overlay;

import net.minecraft.client.renderer.GameRenderer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoATags;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

public final class ScreenOverlayRenderer {
	private static final ConcurrentHashMap<ResourceLocation, Integer> overlays = new ConcurrentHashMap<ResourceLocation, Integer>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderGuiOverlayEvent.Post.class, ScreenOverlayRenderer::onOverlayRender);
	}

	public static void addOverlay(ResourceLocation overlay, int duration) {
		overlays.put(overlay, duration);
	}

	public static void tickOverlays() {
		overlays.entrySet().removeIf(entry -> entry.getValue() <= 0);
		overlays.keySet().spliterator().forEachRemaining(overlay -> overlays.compute(overlay, (overlay2, duration) -> duration - 1));
	}

	public static void clearOverlays() {
		overlays.clear();
	}

	private static void handleToxicWaste(RenderGuiOverlayEvent.Post event) {
		if (event.getOverlay() != VanillaGuiOverlay.HELMET.type())
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();

		if (mc.player != null && mc.player.isEyeInFluid(AoATags.Fluids.TOXIC_WASTE)) {
			final BufferBuilder buff = Tesselator.getInstance().getBuilder();
			float yMod = mc.player.getXRot() / 512f + (mc.player.tickCount / 1800f);
			float xMod = mc.player.getYRot() / 1024f + (mc.player.tickCount / 3600f);

			RenderSystem.enableBlend();
			RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/block/toxic_waste_overlay.png"));
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.3f);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			buff.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			buff.vertex(0.0D, window.getGuiScaledHeight(), -90.0D).uv(xMod, 1f + yMod).endVertex();
			buff.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90.0D).uv(1f + xMod, 1f + yMod).endVertex();
			buff.vertex(window.getGuiScaledWidth(), 0.0, -90.0D).uv(1f + xMod, yMod).endVertex();
			buff.vertex(0.0D, 0.0D, -90.0D).uv(xMod, yMod).endVertex();
			Tesselator.getInstance().end();
			RenderSystem.depthMask(true);
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.disableBlend();
		}
	}

	private static void onOverlayRender(final RenderGuiOverlayEvent.Post event) {
		if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON)
			return;

		handleToxicWaste(event);

		if (event.getOverlay() != VanillaGuiOverlay.HELMET.type() || overlays.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		Tesselator tessellator = Tesselator.getInstance();
		BufferBuilder buffer = tessellator.getBuilder();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		for (Map.Entry<ResourceLocation, Integer> entry : overlays.entrySet()) {
			RenderSystem.setShaderTexture(0, entry.getKey());
			RenderSystem.setShaderColor(1, 1, 1, entry.getValue() / 50f);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
			buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
			tessellator.end();
		}

		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
