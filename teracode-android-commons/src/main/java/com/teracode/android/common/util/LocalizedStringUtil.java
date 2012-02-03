package com.teracode.android.common.util;


/**
 * This class is in charge of handle the internationalized String resources of the application
 * 
 * @author Fernando Perez
 */
public final class LocalizedStringUtil {

	/**
	 * Private constructor to avoid instance creations
	 */
	private LocalizedStringUtil() {
		// Do nothing...
	}

	/**
	 * @param resId The resource id
	 * @return The localized string
	 */
	public static String getString(int resId) {
		return ResourceUtil.getString(resId);
	}
}