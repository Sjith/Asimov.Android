package com.teracode.android.framework.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.application.MessageResourcesSpec;
import com.teracode.android.common.util.ResourceUtil;
import com.teracode.android.framework.activity.AsimovActivity;

/**
 * @author Luciano Rey
 */
public class DefaultDialogFactoryStrategy implements DialogFactoryStrategy {

	/**
	 * @see com.teracode.android.framework.dialog.DialogFactoryStrategy#createProgressDialogForActivity(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public Dialog createProgressDialogForActivity(AsimovActivity<?> activity) {
		ProgressDialog progressDialog = new ProgressDialog((Context) activity);
		progressDialog.setMessage(this.getMessage());
		return progressDialog;
	}

	private String getMessage() {
		MessageResourcesSpec spec = ApplicationProvider.getApp().getMessageResourcesSpec();
		return ResourceUtil.getString(spec.getLoadingMessageResourceId());
	}

}
