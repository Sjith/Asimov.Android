package com.teracode.android.framework.activity;

import android.os.Bundle;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.application.MessageResourcesSpec;
import com.teracode.android.common.exception.ReportExceptionTask;
import com.teracode.android.common.util.AlertDialogUtil;
import com.teracode.android.framework.model.NullModel;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Maxi Rosson
 * @author Fernando Perez
 */
public class ExceptionReportActivity extends BaseActivity<NullModel> implements BaseView {

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#doOnCreate(android.os.Bundle)
	 */
	@Override
	protected void doOnCreate(Bundle savedInstanceState) {
		super.doOnCreate(savedInstanceState);
		setTheme(android.R.style.Theme_NoDisplay);

		MessageResourcesSpec spec = ApplicationProvider.getApp().getMessageResourcesSpec();

		AlertDialogUtil.makeAndShow(spec.getReportDialogTitleResourceId(), android.R.drawable.ic_dialog_alert,
				spec.getReportDialogTextResourceId(), spec.getOkResourceId(), new ReportExceptionTask());
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#createModel()
	 */
	@Override
	protected NullModel createModel() {
		return NullModel.getInstance();
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return -1;
	}

}