package com.teracode.android.demo.activity;

import android.content.Intent;

import com.teracode.android.demo.R;
import com.teracode.android.demo.model.SplashModel;
import com.teracode.android.framework.activity.BaseSplashActivity;

/**
 * @author Fernando Perez
 */
public class SplashActivity extends BaseSplashActivity<SplashModel> {

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return R.layout.splash_activity;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseSplashActivity#startNextActivity()
	 */
	@Override
	protected void startNextActivity() {
		startActivity(new Intent(this, MainMenuActivity.class));
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#createModel()
	 */
	@Override
	protected SplashModel createModel() {
		return new SplashModel(this);
	}

}