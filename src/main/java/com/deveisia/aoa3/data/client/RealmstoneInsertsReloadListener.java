package com.deveisia.aoa3.data.client;

import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.GsonHelper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

public class RealmstoneInsertsReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<String, BlankRealmstoneScreen.RealmstoneWorldInsert> INSERTS = new HashMap<String, BlankRealmstoneScreen.RealmstoneWorldInsert>();
	private static final Gson GSON = new Gson();

	public RealmstoneInsertsReloadListener() {}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		INSERTS.clear();

		for (ResourceLocation resourceLocation : resourceManager.listResources("realmstonegui", path -> path.getPath().endsWith(".json")).keySet()) {
			for (Resource resource : resourceManager.getResourceStack(resourceLocation)) {
				try (InputStream stream = resource.open(); Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
					JsonObject json = GsonHelper.fromJson(GSON, reader, JsonObject.class);

					if (json != null) {
						BlankRealmstoneScreen.RealmstoneWorldInsert insert = BlankRealmstoneScreen.RealmstoneWorldInsert.fromJson(json);

						INSERTS.put(insert.getId(), insert);
					}
					else {
						Logging.logMessage(Level.ERROR, "Realmstone insert json " + resourceLocation.toString() + " appears to be empty or null.");
					}
				}
				catch (RuntimeException | IOException ex) {
					Logging.logMessage(Level.ERROR, "Unable to deserialize realmstone insert json " + resourceLocation + ", skipping.", ex);
				}
			}
		}

		for (BlankRealmstoneScreen.RealmstoneWorldInsert insert : INSERTS.values()) {
			for (String id : insert.getParents()) {
				if (!INSERTS.containsKey(id))
					throw new IllegalArgumentException("Invalid parent node: " + id + " for realmstone insert " + insert.getId());
			}
		}
	}
}
