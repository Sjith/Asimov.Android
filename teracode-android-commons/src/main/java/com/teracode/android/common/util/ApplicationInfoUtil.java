package com.teracode.android.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Fernando Perez
 */
public final class ApplicationInfoUtil {

	private static final String TAG = ApplicationInfoUtil.class.getSimpleName();

	/**
	 * Private constructor to avoid instance creations
	 */
	private ApplicationInfoUtil() {
		// Do nothing...
	}

	/**
	 * @return The version name of the application
	 */
	public static String getVersionName() {
		return getPackageInfo().versionName;
	}

	/**
	 * @return The version code of the application
	 */
	public static int getVersionCode() {
		return getPackageInfo().versionCode;
	}
	
	/**
	 * @return The package name of the application
	 */
	public static String getPackageName() {
		return getPackageInfo().packageName;
	}

	/**
	 * @return The PackageInfo of the application
	 */
	public static PackageInfo getPackageInfo() {
		PackageInfo info = null;

		try {
			Context context = ApplicationProvider.get().getActiveApplication();
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.w(TAG, e.getMessage().toString());
		}

		return info;
	}

	/**
	 * @return {@link ApplicationInfo}
	 */
	public static ApplicationInfo getApplicationInfo() {
		ApplicationInfo info = null;

		try {
			Context context = ApplicationProvider.get().getActiveApplication();
			info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			Log.w(TAG, e.getMessage().toString());
		}

		return info;
	}

	/**
	 * @param view {@link View}
	 */
	public static void hideSoftInput(View view) {
		((InputMethodManager)ApplicationProvider.get().getActiveApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * Gets the {@link WindowManager} from the context.
	 * 
	 * @return {@link WindowManager} The window manager.
	 */
	public static WindowManager getWindowManager() {
		return (WindowManager)ApplicationProvider.get().getActiveApplication().getSystemService(Context.WINDOW_SERVICE);
	}
}