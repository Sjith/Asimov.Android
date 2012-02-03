package com.teracode.android.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Fernando Perez
 * @author Maxi Rosson
 */
public final class ApplicationEnvironmentUtil {

	// Amount of bytes on a megabyte
	private static final int BYTES_TO_MB = 1048576;

	/**
	 * Avoid instantiation
	 */
	private ApplicationEnvironmentUtil() {
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
	public static Integer getVersionCode() {
		return getPackageInfo().versionCode;
	}

	/**
	 * @return The package name of the application
	 */
	public static String getPackageName() {
		return getPackageInfo().packageName;
	}

	/**
	 * @return The name of the application
	 */
	public static String getApplicationName() {
		Context context = ApplicationProvider.get().getActiveApplication();
		ApplicationInfo applicationInfo = ApplicationEnvironmentUtil.getApplicationInfo();
		return context.getPackageManager().getApplicationLabel(applicationInfo).toString();
	}

	public static PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			Context context = ApplicationProvider.get().getActiveApplication();
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// Do Nothing
		}
		return info;
	}

	public static ApplicationInfo getApplicationInfo() {
		ApplicationInfo info = null;
		try {
			Context context = ApplicationProvider.get().getActiveApplication();
			info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// Do Nothing
		}
		return info;
	}

	public static void hideSoftInput(View view) {
		((InputMethodManager) ApplicationProvider.get().getActiveApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * Gets the {@link WindowManager} from the context.
	 * 
	 * @return {@link WindowManager} The window manager.
	 */
	public static WindowManager getWindowManager() {
		return (WindowManager) ApplicationProvider.get().getActiveApplication().getSystemService(Context.WINDOW_SERVICE);
	}

	/**
	 * @return The HEAP size in MegaBytes
	 */
	public static Integer getHeapSize() {
		return ((ActivityManager) ApplicationProvider.get().getActiveApplication().getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
	}

	/**
	 * @return The available storage in MegaBytes
	 */
	public static Long getAvailableInternalDataSize() {
		StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
		long size = stat.getAvailableBlocks() * stat.getBlockSize();
		return size / BYTES_TO_MB;
	}

	/**
	 * @return The total storage in MegaBytes
	 */
	public static Long getTotalInternalDataSize() {
		StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
		long size = stat.getBlockCount() * stat.getBlockSize();
		return size / BYTES_TO_MB;
	}

	public static String getDeviceModel() {
		return android.os.Build.MODEL;
	}

	public static Integer getApiLevel() {
		return android.os.Build.VERSION.SDK_INT;
	}

	public static String getPlatformVersion() {
		return android.os.Build.VERSION.RELEASE;
	}
}
