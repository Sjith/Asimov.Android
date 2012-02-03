package com.teracode.android.framework.module;

import roboguice.config.AbstractAndroidModule;

import com.teracode.android.framework.activity.context.ActivityActionContext;
import com.teracode.android.framework.activity.context.ActivityActionContextImp;
import com.teracode.android.framework.activity.context.ActivityLifeCycle;
import com.teracode.android.framework.activity.context.ActivityLifeCycleImp;

/**
 * @author Fernando Perez
 */
public class AsimovModule extends AbstractAndroidModule {

	/**
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(ActivityLifeCycle.class).to(ActivityLifeCycleImp.class);
		bind(ActivityActionContext.class).to(ActivityActionContextImp.class);
	}
}