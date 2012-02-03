package com.teracode.android.common.util;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Maxi Rosson
 */
public class MeasureUtils {
	
	public static int pxToDp(int px) {
		final float scale = ApplicationProvider.getApp().getResources().getDisplayMetrics().density;
		return (int)(px * scale + 0.5f);
	}
	
}
