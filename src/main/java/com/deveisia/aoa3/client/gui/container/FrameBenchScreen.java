package com.deveisia.aoa3.client.gui.container;

import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import com.deveisia.aoa3.common.container.FrameBenchContainer;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.GuiDataPacket;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FrameBenchScreen extends AbstractContainerScreen<FrameBenchContainer> {
	private static final ResourceLocation textures = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/containers/frame_bench.png");

	private static String currentSelection = "helmet";

	public FrameBenchScreen(FrameBenchContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		currentSelection = "helmet";
	}

	@Override
	public void init() {
		super.init();

		addRenderableWidget(new FrameSelectButton(leftPos + 45, topPos + 33, "Helmet", "helmet", AoAItems.HELMET_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 65, topPos + 33, "Chestplate", "chestplate", AoAItems.CHESTPLATE_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 85, topPos + 33, "Leggings", "leggings", AoAItems.LEGGINGS_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 105, topPos + 33, "Boots", "boots", AoAItems.BOOTS_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 55, topPos + 13, "Crossbow", "crossbow", AoAItems.CROSSBOW_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 75, topPos + 13, "Blaster", "blaster", AoAItems.BLASTER_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 95, topPos + 13, "Cannon", "cannon", AoAItems.CANNON_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 55, topPos + 53, "Gun", "gun", AoAItems.GUN_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 75, topPos + 53, "Shotgun", "shotgun", AoAItems.SHOTGUN_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 95, topPos + 53, "Sniper", "sniper", AoAItems.SNIPER_FRAME.get()));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);

		for (GuiEventListener child : children()) {
			if (child instanceof FrameSelectButton button && button.isHovered())
				guiGraphics.renderTooltip(font, Component.literal(LocaleUtil.getItemName(button.frame)), mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		PoseStack matrix = guiGraphics.pose();
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 165, 256, 256);
	}

	private static class FrameSelectButton extends AbstractWidget {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final String selectionValue;
		private final Item frame;

		private FrameSelectButton(int x, int y, String buttonText, String selectionValue, Item frame) {
			super(x, y, buttonWidth, buttonHeight, Component.translatable(frame.getDescriptionId()));

			this.selectionValue = selectionValue;
			this.frame = frame;
		}

		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			PoseStack matrix = guiGraphics.pose();
			Minecraft mc = Minecraft.getInstance();

			RenderSystem.setShaderTexture(0, textures);
			matrix.pushPose();
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

			isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
			int textureX = 176;
			int textureY = 21 + buttonHeight * (selectionValue.equals(currentSelection) ? 0 : (this.isHovered) ? 2 : 1);

			RenderUtil.renderCustomSizedTexture(matrix, getX(), getY(), textureX, textureY, buttonWidth, buttonHeight, 256, 256);
			matrix.translate(0, 0, 32);
			guiGraphics.renderItem(new ItemStack(frame), getX() + 1, getY() + 1);
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
			matrix.popPose();
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered && !selectionValue.equals(currentSelection);
		}

		@Override
		public void onClick(double p_onClick_1_, double p_onClick_3_) {
			currentSelection = selectionValue;

			AoAPackets.messageServer(new GuiDataPacket(GuiDataPacket.Type.FRAME_BENCH_SELECTION, currentSelection));
			((FrameBenchScreen)Minecraft.getInstance().screen).getMenu().changeSelection(currentSelection);
		}

		@Override
		protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
			defaultButtonNarrationText(narrationElementOutput);
		}
	}
}
