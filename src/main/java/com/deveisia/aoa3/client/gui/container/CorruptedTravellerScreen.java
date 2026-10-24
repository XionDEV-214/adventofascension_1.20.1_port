package com.deveisia.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.common.container.CorruptedTravellerContainer;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import javax.annotation.Nonnull;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class CorruptedTravellerScreen extends AbstractContainerScreen<CorruptedTravellerContainer> {
	private static final ResourceLocation guiTexture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/containers/corrupted_traveller.png");
	private static final ArrayList<Item> validFoods = new ArrayList<Item>();

	private long nextFoodTick = 0;
	private Item currentGhostlyFood = Items.APPLE;
	private final Minecraft mc;

	public CorruptedTravellerScreen(CorruptedTravellerContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

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
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, guiTexture);

		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);
		renderGhostlyFood(matrix, centerX, centerY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		PoseStack matrix = guiGraphics.pose();
		int titleWidth = 4 + mc.font.width(title);

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 0, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 0, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 0, 1, 12, 256, 256);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderUtil.drawTextLegacy(matrix, mc.font, title, 30, 6, ColourUtil.WHITE);
	}

	private void renderGhostlyFood(PoseStack matrix, int centerX, int centerY) {
		Slot slot = menu.getSlot(0);

		if (!slot.hasItem()) {
			ItemStack stack = new ItemStack(getGhostlyFood());
			BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 0);

			matrix.pushPose();
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			mc.textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			matrix.translate(slot.x + centerX, slot.y + centerY, 100.0F);
			matrix.translate(8.0F, 8.0F, 0.0F);
			matrix.scale(1.0F, -1.0F, 1.0F);
			matrix.scale(16.0F, 16.0F, 16.0F);

			MultiBufferSource.BufferSource renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
			boolean diffuseLighting = !model.usesBlockLight();

			if (diffuseLighting)
				Lighting.setupForFlatItems();

			mc.getItemRenderer().render(stack, ItemDisplayContext.GUI, false, matrix, renderTypeBuffer, 100, OverlayTexture.NO_OVERLAY, model);
			renderTypeBuffer.endBatch();
			RenderSystem.enableDepthTest();

			if (diffuseLighting)
				Lighting.setupFor3DItems();

			matrix.popPose();
		}
	}

	private void compileFoodList() {
		for (Item item : ForgeRegistries.ITEMS.getValues()) {
			if (item.isEdible())
				validFoods.add(item);
		}
	}

	@Nonnull
	private Item getGhostlyFood() {
		if (validFoods.isEmpty())
			compileFoodList();

		long worldTick = mc.level.getGameTime();

		if (worldTick >= nextFoodTick) {
			currentGhostlyFood = validFoods.get(RandomUtil.randomNumberUpTo(validFoods.size()));
			nextFoodTick = worldTick + 20;
		}

		return currentGhostlyFood;
	}
}
