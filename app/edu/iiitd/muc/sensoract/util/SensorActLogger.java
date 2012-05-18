/*
 * Name: SensorActLogger.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.util;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * Logger class to log various system messages (informational, warning, error).
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class SensorActLogger extends play.Logger {

	// TODO: Log level
	static {
		// log4j.setLevel(level)
	}

	/**
	 * Logs warning messages.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	public static void warn(final String message) {
		log4j.warn(message);
	}

	/**
	 * Logs warning messages.
	 * 
	 * @param apiname
	 *            Apiname corresponding to this log
	 * @param message
	 *            Message to be logged
	 */
	public static void warn(final String apiname, final String message) {
		log4j.warn(apiname + Const.DELIM1 + message);
	}

	/**
	 * Logs error messages.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	public static void error(final String message) {
		log4j.error(message);
	}

	/**
	 * Logs error messages.
	 * 
	 * @param apiname
	 *            Apiname corresponding to this log
	 * @param message
	 *            Message to be logged
	 */
	public static void error(final String apiname, final String message) {
		log4j.error(apiname + Const.DELIM1 + message);
	}

	/**
	 * Logs informational messages.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	public static void info(final String message) {
		log4j.info(message);
	}

	/**
	 * Logs informational messages.
	 * 
	 * @param apiname
	 *            Apiname corresponding to this log
	 * @param message
	 *            Message to be logged
	 */
	public static void info(final String apiname, final String message) {
		log4j.info(apiname + Const.DELIM1 + message);
	}
}
