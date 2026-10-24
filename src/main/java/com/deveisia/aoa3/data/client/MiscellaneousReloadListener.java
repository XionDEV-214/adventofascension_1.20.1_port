package com.deveisia.aoa3.data.client;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.util.ObjectUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.apache.logging.log4j.Level;

public class MiscellaneousReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<Object, String> DATA = new HashMap<Object, String>();

	public MiscellaneousReloadListener() {}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		try {
			DATA.clear();
			Minecraft mc = Minecraft.getInstance();
			String langCode = mc.options.languageCode;

			if (mc.getLanguageManager().getSelected() != null)
				langCode = mc.getLanguageManager().getSelected();

			Resource wornBook = resourceManager.getResourceOrThrow(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "misc/" + langCode + "/worn_book.txt"));

			DATA.put(AoAItems.WORN_BOOK.get(), ObjectUtil.bufferedReaderToString(new BufferedReader(new InputStreamReader(wornBook.open(), StandardCharsets.UTF_8))));
		}
		catch (IOException ex) {
			Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 additional resources, skipping.", ex);
		}

	}

}
