package com.deveisia.aoa3.integration.patchouli;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import vazkii.patchouli.api.PatchouliAPI;

public final class PatchouliClientIntegration {
	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ScreenEvent.Init.Post.class, PatchouliClientIntegration::onBookOpen);
	}

	private static void onBookOpen(final ScreenEvent.Init.Post ev) {
		ResourceLocation bookId = PatchouliAPI.get().getOpenBookGui();

		if (bookId != null && bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
			AdventGuiTabLore.bookOpened(bookId);
	}
}
