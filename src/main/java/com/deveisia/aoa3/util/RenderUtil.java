package com.deveisia.aoa3.util;

import com.deveisia.aoa3.config.AoAConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

public final class RenderUtil {
	public static GuiGraphics guiGraphicsFor(PoseStack matrix) {
		Minecraft mc = Minecraft.getInstance();
		GuiGraphics guiGraphics = new GuiGraphics(mc, mc.renderBuffers().bufferSource());

		guiGraphics.pose().last().pose().set(matrix.last().pose());
		guiGraphics.pose().last().normal().set(matrix.last().normal());

		return guiGraphics;
	}

	public static void blit(PoseStack matrix, int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(matrix, x, y, u, v, width, height, 256, 256);
	}

	public static void renderTexture(PoseStack matrix, int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(matrix, x, y, u, v, (int)width, (int)height, width, height);
	}

	public static void renderCustomSizedTexture(PoseStack matrix, int x, int y, float u, float v, float uWidth, float vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(matrix, x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public static void renderScaledCustomSizedTexture(PoseStack matrixStack, float x, float y, float u, float v, float uWidth, float vHeight, float renderWidth, float renderHeight, float textureWidth, float textureHeight) {
		BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();
		float widthRatio = 1.0F / textureWidth;
		float heightRatio = 1.0F / textureHeight;

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(matrix, x, y + renderHeight, 0f).uv(u * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y + renderHeight, 0f).uv((u + uWidth) * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y, 0f).uv((u + uWidth) * widthRatio, v * heightRatio).endVertex();
		buffer.vertex(matrix, x, y, 0f).uv(u * widthRatio, v * heightRatio).endVertex();
		BufferUploader.drawWithShader(buffer.end());
	}

	public static void renderFullscreenTexture() {
		Minecraft mc = Minecraft.getInstance();
		float width = mc.getWindow().getGuiScaledWidth();
		float height = mc.getWindow().getGuiScaledHeight();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		BufferBuilder buffer = Tesselator.getInstance().getBuilder();

		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(0, height, -90).uv(0, 1).endVertex();
		buffer.vertex(width, height, -90).uv(1, 1).endVertex();
		buffer.vertex(width, 0, -90).uv(1, 0).endVertex();
		buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
		BufferUploader.drawWithShader(buffer.end());
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

	public static void drawVerticalGradient(PoseStack matrixStack, int x, int y, int z, int width, int height, int topColour, int bottomColour) {
		float alphaTop = (float)(topColour >> 24 & 255) / 255.0F;
		float redTop = (float)(topColour >> 16 & 255) / 255.0F;
		float greenTop = (float)(topColour >> 8 & 255) / 255.0F;
		float blueTop = (float)(topColour & 255) / 255.0F;
		float alphaBottom = (float)(bottomColour >> 24 & 255) / 255.0F;
		float redBottom = (float)(bottomColour >> 16 & 255) / 255.0F;
		float greenBottom = (float)(bottomColour >> 8 & 255) / 255.0F;
		float blueBottom = (float)(bottomColour & 255) / 255.0F;

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);

		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.vertex(matrix, x + width, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		bufferbuilder.vertex(matrix, x + width, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		RenderSystem.disableBlend();
	}

	public static void drawColouredBox(PoseStack matrixStack, int x, int y, int z, int width, int height, int colour) {
		float alpha = (float)(colour >> 24 & 255) / 255.0F;
		float red = (float)(colour >> 16 & 255) / 255.0F;
		float green = (float)(colour >> 8 & 255) / 255.0F;
		float blue = (float)(colour & 255) / 255.0F;

		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.vertex(matrix, (float)x, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x + width, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x + width, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x, (float)y, z).color(red, green, blue, alpha).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		RenderSystem.disableBlend();
	}

	public static void drawTextLegacy(PoseStack matrix, Font font, Component text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, false);
	}

	public static void drawTextShadowLegacy(PoseStack matrix, Font font, Component text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, true);
	}

	public static void drawTextLegacy(PoseStack matrix, Font font, String text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, false);
	}

	public static void drawTextShadowLegacy(PoseStack matrix, Font font, String text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, true);
	}

	public static void drawTextLegacy(PoseStack matrix, Font font, FormattedCharSequence text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, false);
	}

	public static void drawTextShadowLegacy(PoseStack matrix, Font font, FormattedCharSequence text, float x, float y, int colour) {
		drawText(matrix, font, text, x, y, colour, true);
	}

	public static void drawText(PoseStack matrix, Font font, Component text, float x, float y, int colour, boolean shadow) {
		MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

		font.drawInBatch(text, x, y, colour, shadow, matrix.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
		bufferSource.endBatch();
	}

	public static void drawText(PoseStack matrix, Font font, String text, float x, float y, int colour, boolean shadow) {
		MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

		font.drawInBatch(text, x, y, colour, shadow, matrix.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
		bufferSource.endBatch();
	}

	public static void drawText(PoseStack matrix, Font font, FormattedCharSequence text, float x, float y, int colour, boolean shadow) {
		MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

		font.drawInBatch(text, x, y, colour, shadow, matrix.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
		bufferSource.endBatch();
	}

	public static void drawCenteredScaledMessage(PoseStack matrix, Font fontRenderer, Component msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		matrix.pushPose();
		matrix.scale(scale, scale, 1);

		float realX = (x - fontRenderer.width(msg) * scale / 2f) / scale;
		float realY = y / scale;

		drawTextWithType(matrix, fontRenderer, msg, realX, realY, scale, colour, renderType);
		matrix.popPose();
	}

	public static void drawCenteredScaledString(PoseStack matrix, Font fontRenderer, String msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		drawCenteredScaledMessage(matrix, fontRenderer, Component.literal(msg), x, y, scale, colour, renderType);
	}

	public static void drawScaledMessage(PoseStack matrix, Font fontRenderer, Component msg, float x, float y, float scale, int colour, RenderUtil.StringRenderType renderType) {
		float realX = x / scale;
		float realY = y / scale;

		matrix.pushPose();
		matrix.scale(scale, scale, scale);
		drawTextWithType(matrix, fontRenderer, msg, realX, realY, scale, colour, renderType);
		matrix.popPose();
	}

	private static void drawTextWithType(PoseStack matrix, Font fontRenderer, Component msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		if (renderType == StringRenderType.OUTLINED) {
			drawText(matrix, fontRenderer, msg, x, y + (1 / scale), 0, false);
			drawText(matrix, fontRenderer, msg, x, y - (1 / scale), 0, false);
			drawText(matrix, fontRenderer, msg, x + (1 / scale), y, 0, false);
			drawText(matrix, fontRenderer, msg, x - (1 / scale), y, 0, false);
		}

		drawText(matrix, fontRenderer, msg, x, y, colour, renderType == StringRenderType.DROP_SHADOW);
	}

	public static void drawScaledString(PoseStack matrix, Font fontRenderer, String msg, int x, int y, float scale, int colour, RenderUtil.StringRenderType renderType) {
		drawScaledMessage(matrix, fontRenderer, Component.literal(msg), x, y, scale, colour, renderType);
	}

	public static void drawOutlinedText(PoseStack matrix, Font fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		drawText(matrix, fontRenderer, msg, x, y + (1 / currentScale), 0, false);
		drawText(matrix, fontRenderer, msg, x, y - (1 / currentScale), 0, false);
		drawText(matrix, fontRenderer, msg, x + (1 / currentScale), y, 0, false);
		drawText(matrix, fontRenderer, msg, x - (1 / currentScale), y, 0, false);
		drawText(matrix, fontRenderer, msg, x, y, colour, false);
	}

	public static void drawWrappedMessage(PoseStack matrix, Font fontRenderer, FormattedText message, float x, float y, int maxLength, int color) {
		for (FormattedCharSequence processor : fontRenderer.split(message, maxLength)) {
			drawText(matrix, fontRenderer, processor, x, y, color, false);

			y += fontRenderer.lineHeight;
		}
	}

	public static void renderItemInGui(GuiGraphics guiGraphics, ItemStack stack, int x, int y) {
		guiGraphics.renderItem(stack, x, y);
	}

	public static int getPotionGuiRenderOffset() {
		Minecraft mc = Minecraft.getInstance();

		if (mc.player == null || mc.player.getActiveEffects().isEmpty() || AoAConfig.CLIENT.disableHudPotionOffset.get())
			return 0;

		int effectRenderYOffset = 0;

		for (MobEffectInstance effect : mc.player.getActiveEffects()) {
			if (effect.getDuration() > 0 && effect.isVisible()) {
				if (!effect.getEffect().isBeneficial()) {
					effectRenderYOffset = 50;
					break;
				}
				else {
					effectRenderYOffset = 25;
				}
			}
		}

		return effectRenderYOffset;
	}

	public static void renderBlockOutline(VertexConsumer buffer, PoseStack poseStack, float red, float green, float blue, float alpha) {
		Matrix4f pose = poseStack.last().pose();
		float[][] lines = {
				{0, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1}, {0, 1, 1, 0, 1, 0},
				{1, 0, 0, 1, 0, 1}, {1, 0, 1, 0, 0, 1}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0},
				{1, 0, 1, 1, 1, 1}, {1, 0, 0, 1, 1, 0}, {0, 0, 1, 0, 1, 1}, {0, 0, 0, 0, 1, 0}
		};

		for (float[] line : lines) {
			float nx = line[3] - line[0];
			float ny = line[4] - line[1];
			float nz = line[5] - line[2];

			buffer.vertex(pose, line[0], line[1], line[2]).color(red, green, blue, alpha).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
			buffer.vertex(pose, line[3], line[4], line[5]).color(red, green, blue, alpha).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
		}
	}

	public enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}
