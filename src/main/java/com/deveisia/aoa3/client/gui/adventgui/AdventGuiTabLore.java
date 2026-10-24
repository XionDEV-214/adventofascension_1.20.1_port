package com.deveisia.aoa3.client.gui.adventgui;

import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.PatchouliBookOpenPacket;
import com.deveisia.aoa3.common.packet.packets.PatchouliGiveBookPacket;
import com.deveisia.aoa3.integration.IntegrationManager;
import com.deveisia.aoa3.integration.patchouli.PatchouliIntegration;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class AdventGuiTabLore extends Screen {
	private int adjustedMouseX;
	private int adjustedMouseY;

	private static final ConcurrentHashMap<ResourceLocation, ItemStack> loreBooks = new ConcurrentHashMap<ResourceLocation, ItemStack>();

	protected AdventGuiTabLore() {
		super(Component.translatable("gui.aoa3.adventGui.lore"));
	}

	@Override
	protected void init() {
		int index = 0;
		int accumWidth = 0;
		int height = AdventMainGui.scaledTabRootY + 20;

		for (Map.Entry<ResourceLocation, ItemStack> bookEntry : loreBooks.entrySet()) {
			int width = (int)(font.width(bookEntry.getValue().getHoverName()) * 1.5f);

			if (accumWidth + width + 25 + 25 * index >= 764) {
				height += 80;
				accumWidth = 0;
				index = 0;
			}

			addWidget(new PatchouliBook(bookEntry.getKey(), bookEntry.getValue(), AdventMainGui.scaledTabRootX + accumWidth + 25 + 25 * index, height));

			accumWidth += width;
			index++;
		}
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		for (GuiEventListener book : children()) {
			((PatchouliBook)book).render(guiGraphics, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		setDragging(false);

		for (GuiEventListener listener : children()) {
			if (listener.mouseReleased(mouseX, mouseY, button))
				return true;
		}

		return false;
	}

	public static void syncBooks(Collection<ResourceLocation> bookIds) {
		loreBooks.clear();

		if (!IntegrationManager.isPatchouliActive())
			return;

		Item guideBook = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("patchouli", "guide_book"));

		for (ResourceLocation id : bookIds) {
			if (!PatchouliIntegration.isBookLoaded(id))
				continue;

			ItemStack book = new ItemStack(guideBook);
			CompoundTag tag = book.getOrCreateTag();

			tag.putString("patchouli:book", id.toString());
			loreBooks.put(id, book);
		}
	}

	public static void bookOpened(ResourceLocation id) {
		if (!loreBooks.containsKey(id))
			AoAPackets.messageServer(new PatchouliBookOpenPacket(id));
	}

	private static class PatchouliBook extends AbstractWidget {
		private final ResourceLocation id;
		private final ItemStack book;
		private final int titleWidth;

		private boolean mouseHolding = false;

		private PatchouliBook(ResourceLocation id, ItemStack book, int x, int y) {
			super(x, y, 100, 100, book.getDisplayName());

			this.id = id;
			this.book = book;
			this.titleWidth = Minecraft.getInstance().font.width(book.getHoverName());
		}

		protected boolean clicked(double mouseX, double mouseY) {
			return isMouseOver(mouseX, mouseY);
		}

		@Override
		public boolean mouseReleased(double mouseX, double mouseY, int button) {
			if (isValidClickButton(button)) {
				if (!mouseHolding) {
					if (isHovered) {
						PatchouliIntegration.openBook(id);

						return true;
					}
				}
				else {
					mouseHolding = false;

					if (mouseX / AdventMainGui.SCALE < AdventMainGui.scaledRootX ||
							mouseY / AdventMainGui.SCALE < AdventMainGui.scaledRootY ||
							mouseX / AdventMainGui.SCALE > AdventMainGui.scaledRootX + AdventMainGui.BACKGROUND_TEXTURE_WIDTH ||
							mouseY / AdventMainGui.SCALE > AdventMainGui.scaledRootY + AdventMainGui.BACKGROUND_TEXTURE_HEIGHT) {
						Player pl = Minecraft.getInstance().player;
						Item patchouliBook = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("patchouli", "guide_book"));

						if (patchouliBook == null || patchouliBook == Items.AIR)
							return true;

						for (ItemStack stack : pl.getInventory().items) {
							if (stack.getItem() == patchouliBook && stack.hasTag()) {
								CompoundTag bookTag = stack.getTag();

								if (bookTag.contains("patchouli:book") && bookTag.getString("patchouli:book").equals(id.toString()))
									return true;
							}
						}

						AoAPackets.messageServer(new PatchouliGiveBookPacket(id));

						return true;
					}
				}
			}

			return false;
		}

		@Override
		protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
			if (isHovered)
				mouseHolding = true;
		}

		@Override
		public boolean isMouseOver(double mouseX, double mouseY) {
			if (!this.active || !this.visible)
				return false;

			if (mouseX < (getX() + this.titleWidth / 2f - 5) * AdventMainGui.SCALE)
				return false;

			if (mouseY < (getY() + 22 - 5) * AdventMainGui.SCALE)
				return false;

			if (mouseX > (getX() + this.titleWidth / 2f + 32 + 5) * AdventMainGui.SCALE)
				return false;

			return mouseY < (getY() + 22 + 32 + 5) * AdventMainGui.SCALE;
		}

		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			PoseStack matrix = guiGraphics.pose();
			if (this.visible) {
				this.isHovered = isMouseOver(mouseX, mouseY);

				Font fontRenderer = Minecraft.getInstance().font;
				ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
				int itemX = mouseHolding ? (int)(mouseX / AdventMainGui.SCALE - 16) : (int)(getX() + titleWidth / 2f);
				int itemY = mouseHolding ? (int)(mouseY / AdventMainGui.SCALE - 16) : (int)((getY() + 8 * 1.5f) + 10);

				RenderUtil.drawScaledMessage(matrix, fontRenderer, book.getHoverName(), getX(), getY(), 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.DROP_SHADOW);

				matrix.pushPose();
				matrix.translate(itemX, itemY, 0);
				matrix.scale(2f, 2f, 1f);
				guiGraphics.renderItem(book, 0, 0);
				matrix.popPose();

				if (isHovered && !mouseHolding) {
					matrix.pushPose();
					matrix.scale(1.5f, 1.5f, 1.5f);
					guiGraphics.renderTooltip(fontRenderer, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.clickBook"), (int)(mouseX / AdventMainGui.SCALE / 1.5f), (int)(mouseY / AdventMainGui.SCALE / 1.5f));
					matrix.popPose();
				}
			}
		}

		@Override
		protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
			defaultButtonNarrationText(narrationElementOutput);
		}
	}
}
