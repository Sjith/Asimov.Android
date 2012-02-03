package com.teracode.android.framework.dialog;

import android.app.Dialog;

import com.teracode.android.framework.activity.AsimovActivity;

/**
 * @author Luciano Rey
 */
public interface DialogFactoryStrategy {

	/**
	 * @param activity
	 * @return {@link Dialog}
	 */
	public Dialog createProgressDialogForActivity(AsimovActivity<?> activity);

}
