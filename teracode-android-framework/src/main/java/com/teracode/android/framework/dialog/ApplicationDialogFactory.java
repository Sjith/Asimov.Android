package com.teracode.android.framework.dialog;

import android.app.Dialog;

import com.teracode.android.common.util.ClassUtil;
import com.teracode.android.common.util.PropertiesUtil;
import com.teracode.android.framework.activity.AsimovActivity;

/**
 * @author Luciano Rey
 */
public final class ApplicationDialogFactory {

	private static final String DEFAULT_CLASS_NAME = "com.teracode.android.framework.dialog.DefaultDialogFactoryStrategy";
	private static final String PROPERTY_NAME = "dialog.factory.strategy.classname";
	private static ApplicationDialogFactory instance = new ApplicationDialogFactory();
	private final DialogFactoryStrategy strategy;

	private ApplicationDialogFactory() {
		this.strategy = ClassUtil.newInstance(PropertiesUtil.getStringProperty(PROPERTY_NAME, DEFAULT_CLASS_NAME));
	}

	private static ApplicationDialogFactory getInstance() {
		return instance;
	}

	/**
	 * @param activity
	 *            {@link AsimovActivity}
	 * @return {@link Dialog}
	 */
	public static Dialog createProgressDialogForActivity(AsimovActivity<?> activity) {
		return getInstance().strategy.createProgressDialogForActivity(activity);
	}

}
