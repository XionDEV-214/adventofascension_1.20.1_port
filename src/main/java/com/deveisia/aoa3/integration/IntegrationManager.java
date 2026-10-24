package com.deveisia.aoa3.integration;

import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.integration.patchouli.PatchouliIntegration;
import net.minecraftforge.fml.loading.FMLLoader;

public class IntegrationManager {
	static boolean jeiActive = false;
	static boolean patchouliActive = false;
	static boolean kubeJSActive = false;

	public static boolean isJEIActive() {
		return jeiActive;
	}

	public static boolean isPatchouliActive() {
		return patchouliActive;
	}

	public static boolean isKubeJSActive() {
		return kubeJSActive;
	}

	public static void preInit() {
		Logging.logStatusMessage("Checking for third-party integrations");

		if (isModPresent("jei") && AoAConfig.INTEGRATIONS.jeiIntegrationEnabled.get()) {
			Logging.logStatusMessage("Found JEI, integrating");

			jeiActive = true;
		}

		if (isModPresent("patchouli") && AoAConfig.INTEGRATIONS.patchouliEnabled.get()) {
			Logging.logStatusMessage("Found Patchouli, Integrating");

			patchouliActive = true;
			PatchouliIntegration.preInit();
		}

		if (isModPresent("kubejs")) {
			Logging.logStatusMessage("Found KubeJS, Integrating");

			kubeJSActive = true;
		}
	}

	public static void postInit() {}

	public static boolean isModPresent(String modId) {
		return FMLLoader.getLoadingModList().getModFileById(modId) != null;
	}
}
