package com.teracode.android.common.exception.reporter;

import roboguice.service.RoboIntentService;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.google.inject.Inject;
import com.teracode.android.common.mail.MailException;
import com.teracode.android.common.mail.MailService;
import com.teracode.android.common.util.AlarmManagerUtil;
import com.teracode.android.common.util.ApplicationEnvironmentUtil;

/**
 * 
 * @author Maxi Rosson
 */
public class ExceptionReportService extends RoboIntentService {

	private static final String TAG = ExceptionReportService.class.getSimpleName();

	public static final String EXTRA_STACK_TRACE = "stackTrace";
	public static final String EXTRA_EXCEPTION_TIME = "exceptionTime";
	public static final String EXTRA_THREAD_NAME = "threadName";

	// Used internally to count retries.
	private static final String EXTRA_CURRENT_RETRY_COUNT = "currentRetryCount";

	// The maximum number of tries to send a report.
	private static final int MAXIMUM_RETRY_COUNT = 5;

	private static final String NEW_LINE = "\n";

	@Inject
	private MailService mailService;

	public ExceptionReportService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			sendReport(intent);
		} catch (Exception e) {
			Log.e(TAG, "Error while sending an error report", e);
		}
	}

	private void sendReport(Intent intent) {
		Log.v(TAG, "Got request to report error: " + intent.toString());

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("Date: ");
			builder.append(intent.getStringExtra(EXTRA_EXCEPTION_TIME));
			builder.append(NEW_LINE);

			builder.append("Thread Name: ");
			builder.append(intent.getStringExtra(EXTRA_THREAD_NAME));
			builder.append(NEW_LINE);

			builder.append("App Version Code: ");
			builder.append(ApplicationEnvironmentUtil.getVersionCode());
			builder.append(NEW_LINE);

			String versionName = ApplicationEnvironmentUtil.getVersionName();
			builder.append("App Version Name: ");
			builder.append(versionName);
			builder.append(NEW_LINE);

			String packageName = ApplicationEnvironmentUtil.getPackageName();
			builder.append("App Package Name: ");
			builder.append(packageName);
			builder.append(NEW_LINE);

			builder.append("Available Data: ");
			builder.append(ApplicationEnvironmentUtil.getAvailableInternalDataSize());
			builder.append(" MB");
			builder.append(NEW_LINE);

			builder.append("Total Data: ");
			builder.append(ApplicationEnvironmentUtil.getTotalInternalDataSize());
			builder.append(" MB");
			builder.append(NEW_LINE);

			builder.append("Heap Size: ");
			builder.append(ApplicationEnvironmentUtil.getHeapSize());
			builder.append(" MB");
			builder.append(NEW_LINE);

			builder.append("Device Model: ");
			builder.append(ApplicationEnvironmentUtil.getDeviceModel());
			builder.append(NEW_LINE);

			builder.append("API Level: ");
			builder.append(ApplicationEnvironmentUtil.getApiLevel());
			builder.append(NEW_LINE);

			builder.append("Platform Version: ");
			builder.append(ApplicationEnvironmentUtil.getPlatformVersion());
			builder.append(NEW_LINE);
			builder.append(NEW_LINE);

			builder.append(intent.getStringExtra(EXTRA_STACK_TRACE));
			builder.append(NEW_LINE);

			mailService.sendMail("[Android Error] " + packageName + " v" + versionName, builder.toString());

		} catch (MailException e) {
			// Retry at a later point in time
			Log.e(TAG, "Error while sending an error report", e);
			int count = intent.getIntExtra(EXTRA_CURRENT_RETRY_COUNT, 0);
			intent.putExtra(EXTRA_CURRENT_RETRY_COUNT, count + 1);
			PendingIntent operation = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
			if (count >= MAXIMUM_RETRY_COUNT) {
				// Discard error
				Log.w(TAG, "Error report reached the maximum retry count and will be discarded.", e);
				return;
			}
			// Retry every one hour
			long backoff = (count + 1) * 3600000;
			AlarmManagerUtil.scheduleAlarm(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + backoff, operation);
		}
	}
}
