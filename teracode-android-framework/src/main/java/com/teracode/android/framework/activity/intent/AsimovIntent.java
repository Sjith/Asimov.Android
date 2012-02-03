package com.teracode.android.framework.activity.intent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author Luciano Rey
 */
public class AsimovIntent extends Intent {

	public AsimovIntent() {
		super();
	}

	/**
	 * @param packageContext
	 *            {@link Context}
	 * @param cls
	 */
	public AsimovIntent(Context packageContext, Class<?> cls) {
		super(packageContext, cls);
	}

	/**
	 * @param o
	 *            {@link Intent}
	 */
	public AsimovIntent(Intent o) {
		super(o);
	}

	/**
	 * @param action
	 * @param uri
	 * @param packageContext
	 * @param cls
	 */
	public AsimovIntent(String action, Uri uri, Context packageContext, Class<?> cls) {
		super(action, uri, packageContext, cls);
	}

	/**
	 * @param action
	 * @param uri
	 */
	public AsimovIntent(String action, Uri uri) {
		super(action, uri);
	}

	/**
	 * @param action
	 */
	public AsimovIntent(String action) {
		super(action);
	}

	/**
	 * @param intent
	 *            {@link Intent}
	 * @return {@link AsimovIntent}
	 */
	public static AsimovIntent wrap(Intent intent) {
		if (intent instanceof AsimovIntent) {
			return (AsimovIntent) intent;
		} else {
			return new AsimovIntent(intent);
		}
	}

	/**
	 * @param context
	 *            {@link IntentContext}
	 */
	public void setContext(IntentContext context) {
		this.putExtra("_intent_context", context);
	}

	/**
	 * @return {@link IntentContext}
	 */
	public IntentContext getContext() {
		return (IntentContext) this.getSerializableExtra("_intent_context");
	}
}
