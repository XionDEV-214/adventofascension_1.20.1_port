package com.deveisia.aoa3.advent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Logging {
	private static final Logger LOGGER = LogManager.getLogger(AdventOfAscension.MOD_ID);

	public static void logStatusMessage(String message) {
		LOGGER.debug(message);
	}

	public static void logMessage(Level level, String message) {
		LOGGER.log(level, message);
	}

	public static void logMessage(Level level, String message, Exception ex) {
		LOGGER.log(level, message, ex);
	}
}
