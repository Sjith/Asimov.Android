package com.teracode.android.framework.activity;

import roboguice.activity.RoboActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;
import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.activity.context.ActivityLifeCycle;
import com.teracode.android.framework.activity.intent.AsimovIntent;
import com.teracode.android.framework.activity.intent.IntentContext;
import com.teracode.android.framework.dialog.ApplicationDialogFactory;
import com.teracode.android.framework.model.BaseModel;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Fernando Perez
 * 
 * @param <T>
 */
public abstract class BaseActivity<T extends BaseModel<?>> extends RoboActivity implements AsimovActivity<T>, BaseView {

	private static final String BASE_ACTIVITY = "BaseActivity";

	private T model;
	private IntentContext context;
	private Dialog progressDialog;
	@Inject
	private ActivityLifeCycle activityLifeCycle;

	// @Inject
	// private ActivityActionContext activityActionContext;

	// ********************************************
	// *** Activity
	// ********************************************

	/**
	 * @see roboguice.activity.GuiceActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.configView();
		this.setContentView(getLayoutResourceId());

		this.context = getAsimovIntent().getContext();
		this.model = this.createModel();

		this.activityLifeCycle.onCreate(this, savedInstanceState);
		// call hook for subclasses.
		this.doOnCreate(savedInstanceState);
		// init model
		this.runInitModel();
	}

	/**
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		activityLifeCycle.onStop(this);
	}

	/**
	 * @see roboguice.activity.GuiceActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		activityLifeCycle.onStart(this);
	}

	/**
	 * @see roboguice.activity.GuiceActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		activityLifeCycle.onResume(this);
	}

	/**
	 * @see roboguice.activity.GuiceActivity#onRestart()
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		activityLifeCycle.onRestart(this);
	}

	/**
	 * @see roboguice.activity.GuiceActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		activityLifeCycle.onPause(this);
	}

	/**
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityLifeCycle.onDestroy(this);
	}

	// ********************************************
	// *** REFACTORME!!!
	// ********************************************

	// /**
	// * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
	// * android.view.ContextMenu.ContextMenuInfo)
	// */
	// @Override
	// public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	// super.onCreateContextMenu(menu, v, menuInfo);
	// activityActionContext.onCreateContextMenu(menu, v, menuInfo);
	// }
	//
	// /**
	// * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	// */
	// @Override
	// public boolean onMenuItemSelected(int featureId, MenuItem item) {
	// return activityActionContext.onMenuItemSelected(featureId, item);
	// }

	// ********************************************
	// *** BaseView
	// ********************************************

	/**
	 * @see com.teracode.android.framework.view.BaseView#doStartInitModel()
	 */
	@Override
	public final void doStartInitModel() {
		this.showProgressDialog();
		this.onDoStartInitModel();
	}

	/**
	 * @see com.teracode.android.framework.view.BaseView#doStartSuccessfullInitModel()
	 */
	@Override
	public final void doStartSuccessfullInitModel() {
		try {
			this.onDoStartSuccessfullInitModel();
		} finally {
			this.hideProgressDialog();
		}
	}

	/**
	 * @see com.teracode.android.framework.view.BaseView#doStartFailureInitModel(com.teracode.common.exception.AndroidException)
	 */
	@Override
	public final void doStartFailureInitModel(AndroidException exception) {
		try {
			this.onDoStartFailureInitModel(exception);
		} finally {
			this.hideProgressDialog();
		}
	}

	// ********************************************
	// *** AsimovActivity
	// ********************************************

	/**
	 * @see com.teracode.android.framework.activity.AsimovActivity#getModel()
	 */
	@Override
	public final T getModel() {
		return model;
	}

	// ********************************************
	// *** Util's
	// ********************************************

	/**
	 * @return {@link IntentContext}
	 */
	protected IntentContext getContext() {
		return this.context;
	}

	/**
	 * @return {@link AsimovIntent}
	 */
	protected final AsimovIntent getAsimovIntent() {
		return AsimovIntent.wrap(getIntent());
	}

	// ********************************************
	// *** Hooks
	// ********************************************

	protected void onDoStartFailureInitModel(AndroidException exception) {
		Log.d(BASE_ACTIVITY, "onDoStartFailureInitModel()");
	}

	protected void onDoStartSuccessfullInitModel() {
		Log.d(BASE_ACTIVITY, "onStartSuccessfullInit()");
	}

	protected void onDoStartInitModel() {
		Log.d(BASE_ACTIVITY, "onDoStartInitModel()");
	}

	protected void doOnCreate(Bundle savedInstanceState) {
		Log.d(BASE_ACTIVITY, "doOnCreate()");
	}

	protected void configView() {
		Log.d(BASE_ACTIVITY, "configView()");
	}

	protected boolean mustShowProgressDialog() {
		return Boolean.TRUE;
	}

	// ********************************************
	// *** TO REFACTOR!!!!
	// ********************************************

	// /**
	// * @param targetActivityClass
	// * @param context
	// * {@link IntentContext}
	// */
	// protected final void go(Class<?> targetActivityClass, IntentContext context) {
	// AsimovIntent intent = new AsimovIntent(this, targetActivityClass);
	// if (context != null) {
	// intent.setContext(context);
	// }
	// this.startActivity(intent);
	// }
	//
	// /**
	// * @param targetActivityClass
	// */
	// protected final void go(Class<?> targetActivityClass) {
	// this.go(targetActivityClass, null);
	// }

	// ********************************************
	// *** Abstract Method's
	// ********************************************

	/**
	 * @return model.
	 */
	protected abstract T createModel();

	/**
	 * @return
	 */
	protected abstract int getLayoutResourceId();

	// ********************************************
	// *** Private implementation
	// ********************************************

	private void runInitModel() {
		ThreadUtil.execute(new Runnable() {
			@Override
			public void run() {
				model.init(context);
			}
		});
	}

	private void showProgressDialog() {
		if (mustShowProgressDialog()) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (progressDialog == null) {
						progressDialog = ApplicationDialogFactory.createProgressDialogForActivity(BaseActivity.this);
					}
					if (!progressDialog.isShowing()) {
						progressDialog.show();
					}
				}
			});
		}
	}

	private void hideProgressDialog() {
		if (mustShowProgressDialog()) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (progressDialog != null && progressDialog.isShowing()) {
						progressDialog.hide();
					}
				}
			});
		}
	}

}