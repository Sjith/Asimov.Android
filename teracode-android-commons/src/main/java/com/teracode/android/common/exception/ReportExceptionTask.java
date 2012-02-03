package com.teracode.android.common.exception;

import android.app.Activity;
import android.content.Intent;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.exception.reporter.ExceptionReportService;
import com.teracode.android.common.task.Executable;

/**
 * @author Luciano Rey
 */
public class ReportExceptionTask implements Executable {

	/**
	 * @see com.teracode.android.common.task.Executable#execute()
	 */
	@Override
	public void execute() {
		Activity activity = ApplicationProvider.get().getActiveApplication()
				.getCurrentActivity();
		Intent currentIntent = activity.getIntent();
		currentIntent.setClass(activity, ExceptionReportService.class);
		activity.startService(currentIntent);
	}
}