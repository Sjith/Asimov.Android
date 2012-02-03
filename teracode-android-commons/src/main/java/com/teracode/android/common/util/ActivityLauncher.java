package com.teracode.android.common.util;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * This is the dispatcher of the applications. 
 * All the communication between activities should be handled by this entity
 * 
 * @author Fernando Perez
 */
public class ActivityLauncher {

	/**
	 * Launches a new {@link Activity}
	 * 
	 * @param targetActivityClass The target {@link Activity} class to launch
	 */
	public static void launchActivity(Class<? extends Activity> targetActivityClass) {
		Activity currentActivity = ApplicationProvider.get().getActiveApplication().getCurrentActivity();
		if (currentActivity.getClass() != targetActivityClass) {
			Intent intent = new Intent(currentActivity, targetActivityClass);
			currentActivity.startActivity(intent);
		}
	}

	/**
	 * Launches a new {@link Activity}
	 * 
	 * @param targetActivityClass The target {@link Activity} class to launch
	 * @param extraName
	 * @param extraValue
	 */
	public static void launchActivity(Class<? extends Activity> targetActivityClass, String extraName, Serializable extraValue) {
		Activity currentActivity = ApplicationProvider.get().getActiveApplication().getCurrentActivity();
		if (currentActivity.getClass() != targetActivityClass) {
			Intent intent = new Intent(currentActivity, targetActivityClass);
			intent.putExtra(extraName, extraValue);
			currentActivity.startActivity(intent);
		}
	}
}