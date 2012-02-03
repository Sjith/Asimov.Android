package com.teracode.android.common.util;

/**
 * 
 * @author Fernando Perez
 */
public final class StringUtil {

	/**
	 * Private constructor to avoid instance creations
	 */
	public StringUtil() {
		// Do nothing...
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static Boolean isEmpty(String text) {
		return text != null ? text.length() == 0 : true;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		if (s.length() == 0) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
