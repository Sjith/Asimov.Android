package com.teracode.android.common.util;

import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.teracode.android.common.application.ApplicationProvider;

/**
 * This class is in charge of display Toast Messages
 * 
 * @author Fernando Perez
 */
public final class ToastUtil {
	
	private static final Handler HANDLER = new Handler() {
		
		/**
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(android.os.Message msg) {
			String message = (String)msg.obj;
			showToast(message);
		};
	};


	public static void init() {
		//nothing...
	}
	
	/**
	 * Show the message with a Toast in the UI Thread.
	 * 
	 * @param message
	 */
	public static void showOnUIThread(String message) {
		HANDLER.sendMessage(HANDLER.obtainMessage(1, message));
	}

	/**
	 * Show the message with a Toast in the UI Thread.
	 * 
	 * @param messageId
	 */
	public static void showOnUIThread(int messageId) {
		HANDLER.sendMessage(HANDLER.obtainMessage(1, LocalizedStringUtil.getString(messageId)));
	}

	/**
	 * Show the message with a Toast in the current Thread.
	 * 
	 * @param messageId
	 */
	public static void show(int messageId) {
		showToast(LocalizedStringUtil.getString(messageId));
	}

	/**
	 * Show the message with a Toast in the current Thread.
	 * 
	 * @param message
	 */
	public static void show(String message) {
		showToast(message);
	}

	/**
	 * 
	 * @param message
	 */
	private static void showToast(String message) {
		Toast toast = Toast.makeText(ApplicationProvider.get().getActiveApplication(), message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}