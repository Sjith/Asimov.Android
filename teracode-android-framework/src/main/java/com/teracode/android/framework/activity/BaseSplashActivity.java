package com.teracode.android.framework.activity;

import android.view.Window;
import android.view.WindowManager;

import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.model.BaseSplashModel;

/**
 * @author Fernando Perez
 */
public abstract class BaseSplashActivity<T extends BaseSplashModel<?>> extends BaseActivity<T> {

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#onDoStartSuccessfullInitModel()
	 */
	@Override
	protected void onDoStartSuccessfullInitModel() {
		this.validateExpirationTimeSplash();
		this.startNextActivity();
		this.finish();
	}

	/**
	 * 
	 * @see com.teracode.android.framework.activity.BaseActivity#configView()
	 */
	@Override
	protected void configView() {
		if (isFullScreen()) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow()
					.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#mustShowProgressDialog()
	 */
	@Override
	protected final boolean mustShowProgressDialog() {
		return Boolean.FALSE;
	}

	// ********************************************
	// *** Template method's
	// ********************************************

	protected boolean isFullScreen() {
		return true;
	}

	// ********************************************
	// *** Abstract method's
	// ********************************************

	/**
	 * This method should call startActivity to launch a new activity.
	 */
	protected abstract void startNextActivity();

	// ********************************************
	// *** Private implementation
	// ********************************************

	private void validateExpirationTimeSplash() {
		if (getModel().hasExpiredTime()) {
			ThreadUtil.sleepInMillis(getModel().getTimeSleep());
		}
	}
}