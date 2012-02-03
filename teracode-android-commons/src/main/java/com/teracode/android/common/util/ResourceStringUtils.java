package com.teracode.android.common.util;

import android.text.TextUtils;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * This class is in charge of handle the internationalized String resources of the application
 * 
 * @author Fernando Perez
 */
public final class ResourceStringUtils {
	
	/**
	 * Private constructor to avoid instance creations
	 */
	private ResourceStringUtils() {
		// Do nothing...
	}
	
	/**
	 * @param resId The resource id
	 * @return The localized string
	 */
	public static String getString(int resId) {
		return ApplicationProvider.get().getActiveApplication().getString(resId);
	}
	
	/**
	 * Returns a formatted string, using the localized resource as format and the supplied arguments
	 * 
	 * @param resId The resource id to obtain the format
	 * @param args arguments to replace format specifiers
	 * @return The localized and formated string
	 */
	public static String getString(int resId, CharSequence... args) {
		CharSequence text = ApplicationProvider.get().getActiveApplication().getText(resId);
		return TextUtils.expandTemplate(text, args).toString();
	}
	
}
