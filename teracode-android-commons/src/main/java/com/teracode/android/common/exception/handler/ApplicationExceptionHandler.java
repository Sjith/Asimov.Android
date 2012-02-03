package com.teracode.android.common.exception.handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.exception.ApplicationException;
import com.teracode.android.common.exception.code.ErrorCodeRegistry;
import com.teracode.android.common.util.AlertDialogUtil;

/**
 * @author Luciano Rey
 */
public class ApplicationExceptionHandler implements ExceptionHandler<ApplicationException> {

	private static final String OK = "OK";
	private static final String TAG = "Application exception handler";

	/**
	 * @see com.teracode.android.common.exception.handler.ExceptionHandler#handle(com.teracode.android.common.exception.AndroidException)
	 */
	@Override
	public void handle(ApplicationException exception) {
		final Activity currentActivity = ApplicationProvider.get().getActiveApplication().getCurrentActivity();

		String message = ErrorCodeRegistry.get().getMessageFor(exception.getErrorCode());

		Log.e(TAG, message, exception);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(OK, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				currentActivity.finish();
			}
		});
		
		AlertDialogUtil.show(builder);		
	}
	
}
