package com.teracode.android.common.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Maxi Rosson
 * @author Fernando Perez
 */
public final class AlarmManagerUtil {

	/**
	 * Avoid instantiation
	 */
	private AlarmManagerUtil() {
		// Do nothing...
	}

	/**
	 * @param type
	 * @param triggerAtTime
	 * @param operation
	 */
	public static void scheduleAlarm(int type, long triggerAtTime, PendingIntent operation) {
		((AlarmManager) ApplicationProvider.get().getActiveApplication().getSystemService(Context.ALARM_SERVICE)).set(type, triggerAtTime, operation);
	}
}