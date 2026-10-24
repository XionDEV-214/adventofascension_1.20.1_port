package com.deveisia.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.common.container.InfusionTableContainer;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InfusionTableScreen extends AbstractContainerScreen<InfusionTableContainer> {
	private static final ResourceLocation textures = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/containers/infusion_table.png");

	public InfusionTableScreen(InfusionTableContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);
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
		RenderSystem.setShaderColor(1f,1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
