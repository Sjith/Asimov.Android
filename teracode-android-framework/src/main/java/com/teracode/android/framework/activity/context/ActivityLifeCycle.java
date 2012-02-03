package com.teracode.android.framework.activity.context;

import com.teracode.android.framework.activity.AsimovActivity;

import android.os.Bundle;

/**
 * @author Fernando Perez
 * @author Luciano Rey
 */
public interface ActivityLifeCycle {

	/**
	 * @param activity {@link AsimovActivity}
	 * @param savedInstanceState {@link Bundle}
	 */
	public void onCreate(AsimovActivity<?> activity, Bundle savedInstanceState);
	
	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onStart(AsimovActivity<?> activity);

	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onStop(AsimovActivity<?> activity);
	
	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onResume(AsimovActivity<?> activity);
	
	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onRestart(AsimovActivity<?> activity);
	
	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onPause(AsimovActivity<?> activity);

	/**
	 * @param activity {@link AsimovActivity}
	 */
	public void onDestroy(AsimovActivity<?> activity);

}
