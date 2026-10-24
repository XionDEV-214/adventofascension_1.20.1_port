package com.deveisia.aoa3.data.client;

import com.deveisia.aoa3.advent.Logging;
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

public class BestiaryReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<ResourceLocation, String> BESTIARY = new HashMap<ResourceLocation, String>();

	public BestiaryReloadListener() {}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		try {
			BESTIARY.clear();
			Minecraft mc = Minecraft.getInstance();
			String langCode = mc.options.languageCode;

			if (mc.getLanguageManager().getSelected() != null)
				langCode = mc.getLanguageManager().getSelected();

			for (ResourceLocation resourceLocation : resourceManager.listResources("bestiary/" + langCode, path -> path.getPath().endsWith(".txt")).keySet()) {
				for (Resource resource : resourceManager.getResourceStack(resourceLocation)) {
					String relativePath = resourceLocation.getPath().substring(15);
					String[] pathParts = relativePath.split("/");

					if (pathParts.length < 2) {
						Logging.logMessage(Level.DEBUG, "Invalid resource path for bestiary entry, skipping. " + relativePath);

						continue;
					}

					ResourceLocation entryId = ResourceLocation.fromNamespaceAndPath(pathParts[0], pathParts[1].substring(0, pathParts[1].length() - 4));

					BESTIARY.put(entryId, ObjectUtil.bufferedReaderToString(new BufferedReader(new InputStreamReader(resource.open(), StandardCharsets.UTF_8))));
				}
			}
		}
		catch (IOException ex) {
			Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 Bestiary resources, skipping.", ex);
		}

	}

}
