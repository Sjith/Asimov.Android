package com.teracode.android.framework.activity.context;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.framework.activity.AsimovActivity;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Fernando Perez
 * @author Luciano Rey
 */
public class ActivityLifeCycleImp implements ActivityLifeCycle {

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onCreate(com.teracode.android.framework.activity.AsimovActivity, android.os.Bundle)
	 */
	@Override
	public void onCreate(AsimovActivity<?> activity, Bundle savedInstanceState) {
		ApplicationProvider.getApp().setCurrentActivity((Activity) activity);
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onStart(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onStart(AsimovActivity<?> activity) {
		ApplicationProvider.getApp().setCurrentActivity((Activity) activity);
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onStop(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onStop(AsimovActivity<?> activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onResume(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onResume(AsimovActivity<?> activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onRestart(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onRestart(AsimovActivity<?> activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onPause(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onPause(AsimovActivity<?> activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityLifeCycle#onDestroy(com.teracode.android.framework.activity.AsimovActivity)
	 */
	@Override
	public void onDestroy(AsimovActivity<?> activity) {
		// TODO Auto-generated method stub
	}

}