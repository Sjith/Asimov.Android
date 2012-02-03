package com.teracode.android.common.exception.reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.application.MessageResourcesSpec;
import com.teracode.android.common.util.DateUtil;
import com.teracode.android.common.util.IdGeneratorUtil;
import com.teracode.android.common.util.LocalizedStringUtil;
import com.teracode.android.common.util.NotificationUtil;

/**
 * @author Maxi Rosson
 * @author Fernando Perez
 */
public class ExceptionReporter {

	/**
	 * Sends an error report.
	 * 
	 * @param thread
	 *            The thread where the exception occurred (e.g. {@link java.lang.Thread#currentThread()})
	 * @param ex
	 *            The exception
	 */
	public void reportException(Thread thread, Throwable ex, Class<RoboActivity> reportActivity) {
		Writer writer = new StringWriter();
		ex.printStackTrace(new PrintWriter(writer));

		Date now = DateUtil.now();
		Bundle bundle = new Bundle();
		bundle.putString(ExceptionReportService.EXTRA_THREAD_NAME, thread.getName());
		bundle.putString(ExceptionReportService.EXTRA_EXCEPTION_TIME,
				DateUtil.unTransform(now, DateUtil.YYYYMMDDHHMMSSZ_DATE_FORMAT));
		bundle.putString(ExceptionReportService.EXTRA_STACK_TRACE, writer.toString());

		MessageResourcesSpec resourcesSpec = ApplicationProvider.getApp().getMessageResourcesSpec();

		String notificationTitle = LocalizedStringUtil.getString(resourcesSpec.getNotificationErrorTitleResourceId());
		String notificationText = LocalizedStringUtil.getString(resourcesSpec.getNotificationErrorTextResourceId());

		NotificationUtil.sendNotification(IdGeneratorUtil.getRandomIntId(), android.R.drawable.stat_notify_error,
				notificationTitle, notificationTitle, notificationText, reportActivity, now.getTime(), bundle);
	}
}