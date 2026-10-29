package com.deveisia.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.common.container.BankerContainer;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BankerScreen extends AbstractContainerScreen<BankerContainer> {
	private static final ResourceLocation guiTexture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/containers/banker.png");
	private final Minecraft mc;
	public BankerScreen(BankerContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.imageHeight = 249;
		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		PoseStack matrix = guiGraphics.pose();
		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, guiTexture);

		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);

		if (mc.player != null)
			renderCoinPlaceholders(guiGraphics, centerX, centerY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		PoseStack matrix = guiGraphics.pose();
		int titleWidth = 4 + mc.font.width(title);

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 15, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 15, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 15, 1, 12, 256, 256);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderUtil.drawTextLegacy(matrix, mc.font, title, 30, 6, ColourUtil.WHITE);
	}

	private void renderCoinPlaceholders(GuiGraphics guiGraphics, int centerX, int centerY) {
		for (int i = 0; i < BankerContainer.COIN_SLOTS; i++) {
			Slot slot = menu.getSlot(i);

			if (slot.getItem().isEmpty()) {
				ItemStack coinStack = new ItemStack(BankerContainer.getCoinForSlot(i), BankerContainer.getCountForSlot(i));

				guiGraphics.renderFakeItem(coinStack, slot.x + centerX, slot.y + centerY);
				guiGraphics.renderItemDecorations(mc.font, coinStack, slot.x + centerX, slot.y + centerY);
			}
		}
	}
}
