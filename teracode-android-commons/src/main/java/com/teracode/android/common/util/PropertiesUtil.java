package com.teracode.android.common.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.teracode.android.common.exception.code.CommonErrorCode;

/**
 * @author Luciano Rey
 */
public final class PropertiesUtil {

	private static final String PROPERTIES_FILE = "settings.properties";
	private static final String LOCAL_PROPERTIES_FILE = "settings.local.properties";

	private static PropertiesUtil INSTANCE = new PropertiesUtil();

	private Properties properties;

	/**
	 * Private constructor to avoid instance creations
	 */
	private PropertiesUtil() {
		try {
			URL url = this.getClass().getClassLoader().getResource(LOCAL_PROPERTIES_FILE);
			if (url == null) {
				url = this.getClass().getClassLoader().getResource(PROPERTIES_FILE);
			}
			properties = new Properties();
			properties.load(url.openStream());
		} catch (IOException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
		}
	}

	public static void load() {
		// nothig...
	}

	/**
	 * @param name property.
	 * @return the named property value, or null if it can't be found.
	 */
	public static String getStringProperty(String name) {
		return getInstance().properties.getProperty(name);
	}

	/**
	 * @param name
	 * @param defaultValue
	 * @return the named property value, or defaultValue if it can't be found.
	 */
	public static String getStringProperty(String name, String defaultValue) {
		String value = getStringProperty(name);
		return value != null ? value : defaultValue;
	}

	/**
	 * @param name
	 * @return the named property value, or null if it can't be found.
	 */
	public static Integer getIntegerProperty(String name) {
		return PropertiesUtil.getIntegerProperty(name, null);
	}

	/**
	 * @param name
	 * @param defaultValue
	 * @return the named property value, or defaultValue if it can't be found.
	 */
	public static Integer getIntegerProperty(String name, Integer defaultValue) {
		String value = getStringProperty(name);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * @param name
	 * @return the named property value, or null if it can't be found.
	 */
	public static Boolean getBooleanProperty(String name) {
		return PropertiesUtil.getBooleanProperty(name, null);
	}

	/**
	 * @param name
	 * @param defaultValue
	 * @return the named property value, or defaultValue if it can't be found.
	 */
	public static Boolean getBooleanProperty(String name, Boolean defaultValue) {
		String value = getStringProperty(name);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	/**
	 * @return {@link PropertiesUtil}
	 */
	private static PropertiesUtil getInstance() {
		return INSTANCE;
	}
}
