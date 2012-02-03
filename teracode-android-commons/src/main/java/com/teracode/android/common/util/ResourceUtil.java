package com.teracode.android.common.util;

import android.R.drawable;
import android.graphics.drawable.Drawable;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Fernando Perez
 */
public final class ResourceUtil {

	private ResourceUtil() {
		// Do nothing...
	}

	/**
	 * @param resId
	 * @return
	 */
	public static String getString(int resId) {
		return ApplicationProvider.get().getActiveApplication().getString(resId);
	}

	/**
	 * @param resId
	 * @return {@link drawable}
	 */
	public static Drawable getDrawable(int resId) {
		return ApplicationProvider.get().getActiveApplication().getResources().getDrawable(resId);
	}
}