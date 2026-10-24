package com.deveisia.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.common.container.DivineStationContainer;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DivineStationScreen extends AbstractContainerScreen<DivineStationContainer> {
	private static final ResourceLocation textures = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/containers/basic_block.png");

	public DivineStationScreen(DivineStationContainer container, Inventory inv, Component guiTitle) {
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
		DivineStationContainer container = getMenu();

		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 141, 256, 256);

		if ((!container.inputs.getItem(0).isEmpty() || !container.inputs.getItem(1).isEmpty()) && container.output.getItem(0).isEmpty())
			RenderUtil.renderCustomSizedTexture(matrix, leftPos + 99, topPos + 21, imageWidth, 0, 28, 21, 256, 256);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		PoseStack matrix = guiGraphics.pose();
		RenderUtil.drawCenteredScaledMessage(matrix, Minecraft.getInstance().font, getTitle(), 88, 6, 1, 4210752, RenderUtil.StringRenderType.NORMAL);
	}
}
