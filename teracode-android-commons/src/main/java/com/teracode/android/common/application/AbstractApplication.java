package com.teracode.android.common.application;

import java.io.File;

import roboguice.application.RoboApplication;
import android.app.Activity;

import com.teracode.android.common.util.AlertDialogUtil;
import com.teracode.android.common.util.PropertiesUtil;
import com.teracode.android.common.util.ToastUtil;

/**
 * This class is in charge of initialize the Application configuration.
 * 
 * @author Fernando Perez
 */
public abstract class AbstractApplication extends RoboApplication {

	private Activity currentActivity;

	/**
	 * Constructor
	 */
	public AbstractApplication() {
		ApplicationProvider.get().loadActiveApplication(this);
	}

	/**
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		// init util for toast messaging...
		ToastUtil.init();
		// init util for dialog messaging...
		AlertDialogUtil.init();
		// load properties...
		PropertiesUtil.load();
	}

	/**
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * @see android.app.Application#onLowMemory()
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	/**
	 * All the activities should call this method on the onStart() to set itself
	 * as the current activity
	 * 
	 * @param activity
	 *            {@link Activity}
	 */
	public void setCurrentActivity(Activity activity) {
		this.currentActivity = activity;
	}

	/**
	 * @return The current visible {@link Activity}
	 */
	public Activity getCurrentActivity() {
		return this.currentActivity;
	}

	/**
	 * @return {@link File}
	 */
	public abstract File getCacheDirectory();

	/**
	 * @return {@link MessageResourcesSpec}
	 */
	public abstract MessageResourcesSpec getMessageResourcesSpec();

}