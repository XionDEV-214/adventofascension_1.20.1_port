package com.deveisia.aoa3.client.gui.adventgui;

import net.minecraft.client.gui.narration.NarrationElementOutput;
import com.deveisia.aoa3.util.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.AoAKeybinds;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.data.client.AdventGuiThemeReloadListener;
import com.deveisia.aoa3.integration.IntegrationManager;
import com.deveisia.aoa3.util.*;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Collections;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.achievement.StatsUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class AdventMainGui extends Screen implements StatsUpdateListener {
	private static final ResourceLocation TITLE = HolidayUtil.isAprilFools() ? ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/adventgui/aoa_title_alt.png") : ResourceLocation.fromNamespaceAndPath("aoa3", "textures/gui/adventgui/aoa_title.png");
	protected static final int GUI_WIDTH = 1024;
	protected static final int GUI_HEIGHT = 512;
	protected static final int BACKGROUND_TEXTURE_WIDTH = 976;
	protected static final int BACKGROUND_TEXTURE_HEIGHT = 480;
	protected static final float SCALE = 0.45f;

	private static AdventMainGui instance;

	public static String currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();
	protected static AdventGuiThemeReloadListener.AdventGuiTheme theme = null;

	private static ADVENT_WINDOW_TAB selectedTab = ADVENT_WINDOW_TAB.PLAYER;
	@Nullable
	private static Screen tabScreen;

	protected Player player;

	protected static int scaledRootX;
	protected static int scaledRootY;
	protected static int scaledTabRootX;
	protected static int scaledTabRootY;

	private static int updateMessageTicker = 0;

	public AdventMainGui(Player player) {
		super(Component.translatable("gui.aoa3.adventGui.title"));

		this.player = player;
		instance = this;
		tabScreen = null;
		currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();
	}

	@Override
	protected void init() {
		theme = AdventGuiThemeReloadListener.setTheme(AoAConfig.CLIENT.adventGuiTheme.get());

		addRenderableWidget(new AdventMainGuiTabButton(11, 129, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.stats"), ADVENT_WINDOW_TAB.PLAYER));
		addRenderableWidget(new AdventMainGuiTabButton(11, 199, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.bestiary"), ADVENT_WINDOW_TAB.BESTIARY));
		addRenderableWidget(new AdventMainGuiTabButton(11, 269, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.lore"), ADVENT_WINDOW_TAB.LORE));
		addRenderableWidget(new AdventMainGuiTabButton(11, 339, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.leaderboards"), ADVENT_WINDOW_TAB.LEADERBOARDS));
		addRenderableWidget(new AdventMainGuiTabButton(11, 409, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help"), ADVENT_WINDOW_TAB.HELP));

		correctGuiPositions();
		initTabScreen();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		PoseStack matrix = guiGraphics.pose();
		renderBackground(guiGraphics);

		RenderSystem.setShaderTexture(0, theme.getBackgroundTexture());

		matrix.pushPose();
		RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.scale(SCALE, SCALE, SCALE);

		RenderUtil.renderCustomSizedTexture(matrix, scaledRootX, scaledRootY, 24, 16, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, GUI_WIDTH, GUI_HEIGHT);
		RenderSystem.setShaderTexture(0, TITLE);
		RenderUtil.renderCustomSizedTexture(matrix, scaledRootX - ((GUI_WIDTH - BACKGROUND_TEXTURE_WIDTH) / 2) + 68, scaledRootY - ((GUI_HEIGHT - BACKGROUND_TEXTURE_HEIGHT) / 2) + 21, 0, 0, 892, 112, 892, 112);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

		if (theme.getOverlayTexture() != null) {
			RenderSystem.setShaderTexture(0, theme.getOverlayTexture());
			RenderSystem.enableBlend();
			RenderUtil.renderCustomSizedTexture(matrix, scaledRootX - ((GUI_WIDTH - BACKGROUND_TEXTURE_WIDTH) / 2), scaledRootY - ((GUI_HEIGHT - BACKGROUND_TEXTURE_HEIGHT) / 2), 0, 0, GUI_WIDTH, GUI_HEIGHT, GUI_WIDTH, GUI_HEIGHT);
			RenderSystem.disableBlend();
		}

		RenderUtil.drawScaledMessage(matrix, font, Component.literal("v" + AdventOfAscension.VERSION), scaledRootX + 175, scaledRootY + 85, 1.25f, ColourUtil.RGB(255, 223, 0), RenderUtil.StringRenderType.DROP_SHADOW);

		if (WebUtil.isUpdateAvailable()) {
			updateMessageTicker--;

			if (updateMessageTicker <= -30)
				updateMessageTicker = 90;

			if (updateMessageTicker > 0) {
				Component msg = LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.update", Component.literal(WebUtil.getLatestVersion()));

				RenderUtil.drawScaledMessage(matrix, font, msg, scaledRootX + 925 - font.width(msg), scaledRootY + 105, 1.25f, ColourUtil.RGB(229, 0, 0), RenderUtil.StringRenderType.DROP_SHADOW);
			}
		}

		if (tabScreen != null)
			tabScreen.render(guiGraphics, mouseX, mouseY, partialTicks);

		RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.popPose();
	}

	public static boolean isBlockingKeys() {
		if (tabScreen instanceof AdventGuiTabBestiary) {
			EditBox bestiarySearchField = ((AdventGuiTabBestiary)tabScreen).searchField;

			return bestiarySearchField != null && bestiarySearchField.isFocused();
		}

		return false;
	}

	@Override
	public void onStatsUpdated() {
		if (tabScreen instanceof StatsUpdateListener)
			((StatsUpdateListener)tabScreen).onStatsUpdated();
	}

	protected enum ADVENT_WINDOW_TAB {
		PLAYER,
		BESTIARY,
		LORE,
		LEADERBOARDS,
		HELP
	}

	private static class AdventMainGuiTabButton extends AbstractWidget {
		private static final int buttonWidth = 180;
		private static final int buttonHeight = 60;

		private final ADVENT_WINDOW_TAB tabID;

		private AdventMainGuiTabButton(int x, int y, Component buttonText, ADVENT_WINDOW_TAB tab) {
			super(x, y, buttonWidth, buttonHeight, buttonText);

			this.tabID = tab;

			if (tab == ADVENT_WINDOW_TAB.LEADERBOARDS) {
				this.active = false;
			}
			else if (tab == ADVENT_WINDOW_TAB.LORE && !IntegrationManager.isPatchouliActive()) {
				this.active = false;
			}
		}

		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			PoseStack matrix = guiGraphics.pose();
			Minecraft mc = Minecraft.getInstance();

			RenderSystem.setShaderTexture(0, theme.getMenuButtonTexture());
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

			isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
			int textureX = 0;
			int textureY = buttonHeight * (tabID == selectedTab ? 0 : (this.active && this.isHovered) ? 1 : 2);

			RenderUtil.renderScaledCustomSizedTexture(matrix, scaledRootX + getX(), scaledRootY + getY(), textureX, textureY, 180, 60, 180, 60, 180, 180);

			int stringColour = ColourUtil.RGB(239, 137, 119);

			if (getFGColor() != 0) {
				stringColour = getFGColor();
			}
			else if (!this.active) {
				stringColour = 10526880;
			}
			else if (this.isHovered || tabID == selectedTab) {
				stringColour = ColourUtil.RGB(247, 239, 0);
			}

			RenderUtil.drawCenteredScaledMessage(matrix, mc.font, getMessage(), scaledRootX + getX() + 90, scaledRootY + getY() + 25, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);

			if (isHovered && this.tabID == ADVENT_WINDOW_TAB.LORE && !this.active) {
				matrix.pushPose();
				matrix.scale(2f, 2f, 2f);
				guiGraphics.renderTooltip(mc.font, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.patchouli"), (int)(mouseX / SCALE / 2), (int)(mouseY / SCALE / 2));
				matrix.popPose();
			}
		}

		@Override
		protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
			defaultButtonNarrationText(narrationElementOutput);
		}

		@Override
		protected boolean clicked(double mouseX, double mouseY) {
			return active && isHovered && selectedTab != tabID;
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			AdventMainGui.selectedTab = tabID;
			AdventMainGui.tabScreen = null;
			AdventMainGui.instance.initTabScreen();
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((scaledRootX + buttonX) * SCALE) && mouseX <= (int)((scaledRootX + buttonX + buttonWidth) * SCALE) && mouseY >= (int)((scaledRootY + buttonY) * SCALE) && mouseY <= (int)((scaledRootY + buttonY + buttonHeight) * SCALE);
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		if (tabScreen != null)
			return tabScreen.mouseScrolled(mouseX, mouseY, scrollAmount);

		return super.mouseScrolled(mouseX, mouseY, scrollAmount);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (tabScreen != null && tabScreen.mouseClicked(mouseX, mouseY, button))
			return true;

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (tabScreen != null && tabScreen.mouseReleased(mouseX, mouseY, button))
			return true;

		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (tabScreen != null && tabScreen.mouseDragged(mouseX, mouseY, button, dragX, dragY))
			return true;

		return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (!isBlockingKeys() && keyCode == AoAKeybinds.ADVENT_GUI.getKey().getValue()) {
			getMinecraft().setScreen(null);

			return true;
		}

		boolean used = false;

		if (tabScreen != null)
			used = tabScreen.keyPressed(keyCode, scanCode, modifiers);

		return super.keyPressed(keyCode, scanCode, modifiers) || used;
	}

	@Override
	public boolean charTyped(char character, int arg) {
		if (tabScreen != null)
			return tabScreen.charTyped(character, arg);

		return super.charTyped(character, arg);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if (!super.keyReleased(keyCode, scanCode, modifiers)) {
			if (tabScreen != null)
				return tabScreen.keyReleased(keyCode, scanCode, modifiers);

			return false;
		}

		return true;
	}

	private void initTabScreen() {
		Minecraft mc = getMinecraft();
		int tabWidth = (int)((width + 182) * SCALE);
		int tabHeight =  (int)((height + 118) * SCALE);

		if (tabScreen != null) {
			tabScreen.init(mc, tabWidth, tabHeight);
		}
		else {
			switch (selectedTab) {
				case PLAYER:
					tabScreen = new AdventGuiTabPlayer();
					tabScreen.init(mc, tabWidth, tabHeight);
					break;
				case HELP:
					tabScreen = new AdventGuiTabHelp();
					tabScreen.init(mc, tabWidth, tabHeight);
					break;
				case BESTIARY:
					tabScreen = new AdventGuiTabBestiary();
					tabScreen.init(mc, tabWidth, tabHeight);
					break;
				case LORE:
					tabScreen = new AdventGuiTabLore();
					tabScreen.init(mc, tabWidth, tabHeight);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void correctGuiPositions() {
		Window window = getMinecraft().getWindow();

		scaledRootX = (int) (Math.round((window.getGuiScaledWidth() / 2d) / SCALE) - Math.round(BACKGROUND_TEXTURE_WIDTH / 2d));
		scaledRootY = (int) (Math.round((window.getGuiScaledHeight() / 2d) / SCALE) - Math.round(BACKGROUND_TEXTURE_HEIGHT / 2d));
		scaledTabRootX = scaledRootX + 201;
		scaledTabRootY = scaledRootY + 129;
	}

	@Override
	public void resize(Minecraft mc, int width, int height) {
		super.resize(mc, width, height);

		correctGuiPositions();

		if (tabScreen != null)
			tabScreen.resize(mc, (int)((width + 182) * SCALE), (int)((height + 118) * SCALE));
	}

	@Override
	public void removed() {
		super.removed();

		if (tabScreen != null)
			tabScreen.removed();
	}

	public static void changeTheme() {
		theme = AdventGuiThemeReloadListener.getNextTheme();

		AoAConfig.CLIENT.changeAdventGuiTheme(theme.getName());
	}
}
