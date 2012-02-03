package com.teracode.android.common.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.task.Executable;

/**
 * @author Fernando Perez
 */
public final class AlertDialogUtil {

	private static final int BUILDER = 1;
	private static final int DIALOG = 2;

	private static final Handler HANDLER = new Handler() {

		/**
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case BUILDER:
				AlertDialog.Builder builder = (Builder) msg.obj;
				builder.create().show();
				break;
			case DIALOG:
				AlertDialog dialog = (AlertDialog) msg.obj;
				dialog.show();
				break;
			}
		};
	};

	public static void init() {
		// nohthing...
	}

	/**
	 * Show the alert dialog in the UI Thread
	 * 
	 * @param builder
	 */
	public static void show(AlertDialog.Builder builder) {
		send(obtainMessage(BUILDER, builder));
	}

	/**
	 * Show the alert dialog in the UI Thread
	 * 
	 * @param alertDialog
	 */
	public static void show(AlertDialog alertDialog) {
		send(obtainMessage(DIALOG, alertDialog));
	}

	/**
	 * @param what
	 * @param object
	 * @return {@link Message}
	 */
	private static Message obtainMessage(int what, Object object) {
		return HANDLER.obtainMessage(what, object);
	}

	/**
	 * @param message {@link Message}
	 */
	private static void send(Message message) {
		HANDLER.sendMessage(message);
	}

	public static void makeAndShow(int dialogTitle, int dialogIcon, int dialogText, int ok, final Executable actionExecutable) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(ApplicationProvider.get().getActiveApplication().getCurrentActivity());

		dialog.setTitle(dialogTitle);
		dialog.setIcon(dialogIcon);
		dialog.setMessage(dialogText);

		dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

			/**
			 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
			 */
			@Override
			public void onClick(DialogInterface dialog, int which) {
				actionExecutable.execute();

				dialog.dismiss();
				ApplicationProvider.get().getActiveApplication().getCurrentActivity().finish();
			}
		});

		dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

			/**
			 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
			 */
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				ApplicationProvider.get().getActiveApplication().getCurrentActivity().finish();
			}
		});

	}
}