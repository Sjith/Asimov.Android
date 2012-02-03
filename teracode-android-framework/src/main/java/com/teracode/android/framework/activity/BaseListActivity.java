package com.teracode.android.framework.activity;

import roboguice.activity.RoboListActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.inject.Inject;
import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.activity.context.ActivityLifeCycle;
import com.teracode.android.framework.activity.intent.AsimovIntent;
import com.teracode.android.framework.activity.intent.IntentContext;
import com.teracode.android.framework.dialog.ApplicationDialogFactory;
import com.teracode.android.framework.model.BaseListModel;
import com.teracode.android.framework.view.BaseListView;

/**
 * @author Luciano Rey
 */
public abstract class BaseListActivity<E extends Object, T extends BaseListModel<E, ?>> extends RoboListActivity
		implements AsimovActivity<T>, BaseListView, OnItemClickListener, OnClickListener {

	private static final CustomHandler HANDLER = new CustomHandler();

	// ********************************************
	// *** Field's
	// ********************************************

	private T model;
	private IntentContext context;
	@Inject
	private ActivityLifeCycle activityLifeCycle;
	private ListView listView;
	private ArrayAdapter<E> listAdapter;
	private Dialog progressDialog;
	private View moreElementsView;

	// ********************************************
	// *** Activity
	// ********************************************

	/**
	 * @see roboguice.activity.GuiceListActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.configView();
		this.setContentView(this.getLayoutResourceId());

		this.context = getAsimovIntent().getContext();
		this.model = this.createModel();

		this.activityLifeCycle.onCreate(this, savedInstanceState);
		// call hook for subclasses.
		this.doOnCreate(savedInstanceState);

		// create adapter.
		this.listAdapter = this.makeListAdapter();
		// find listview.
		this.listView = this.findListView();
		// create more elements view.
		this.moreElementsView = this.getMoreElementsView();
		if (this.moreElementsView != null) {
			this.listView.addFooterView(this.moreElementsView);
			this.moreElementsView.setOnClickListener(this);
			this.moreElementsView.setVisibility(View.GONE);
		}
		// configure listview.
		this.listView.setAdapter(this.listAdapter);
		this.listView.setOnItemClickListener(this);

		// init model
		this.runInitModel();
	}

	/**
	 * @see roboguice.activity.RoboListActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		this.activityLifeCycle.onStart(this);
	}

	/**
	 * @see roboguice.activity.RoboListActivity#onRestart()
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		this.activityLifeCycle.onRestart(this);
	}

	/**
	 * @see roboguice.activity.RoboListActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.activityLifeCycle.onResume(this);
	}

	/**
	 * @see roboguice.activity.RoboListActivity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		this.activityLifeCycle.onStop(this);
	}

	/**
	 * @see roboguice.activity.RoboListActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.activityLifeCycle.onDestroy(this);
	}

	// ********************************************
	// *** OnItemClickListener
	// ********************************************

	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View,
	 *      int, long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		this.model.onElementSelected((E) parent.getItemAtPosition(position));
	}

	// ********************************************
	// *** OnClickListener
	// ********************************************

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public final void onClick(View v) {
		if (v == this.moreElementsView) {
			ThreadUtil.execute(new Runnable() {
				@Override
				public void run() {
					model.loadMoreElements();
				}
			});
		}
	}

	// ********************************************
	// *** AsimovActivity
	// ********************************************

	/**
	 * @see com.teracode.android.framework.activity.AsimovActivity#getModel()
	 */
	@Override
	public T getModel() {
		return this.model;
	}

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
			this.fillListView();
			this.hideProgressDialog();
		}
	}

	/**
	 * @see com.teracode.android.framework.view.BaseView#doStartFailureInitModel(com.teracode.android.common.exception.AndroidException)
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
	// *** BaseListView
	// ********************************************

	/**
	 * @see com.teracode.android.framework.view.BaseListView#doStartLoadMoreElements()
	 */
	@Override
	public final void doStartLoadMoreElements() {
		this.showProgressDialog();
	}

	/**
	 * @see com.teracode.android.framework.view.BaseListView#doStartSuccessfullLoadMoreElements()
	 */
	@Override
	public final void doStartSuccessfullLoadMoreElements() {
		this.fillListView();
		this.hideProgressDialog();
	}

	/**
	 * @see com.teracode.android.framework.view.BaseListView#doStartFailureLoadMoreElements(com.teracode.android.common.exception.AndroidException)
	 */
	@Override
	public final void doStartFailureLoadMoreElements(AndroidException exception) {
		this.hideProgressDialog();
	}

	// ********************************************
	// *** Hook's
	// ********************************************

	protected void onDoStartFailureInitModel(AndroidException exception) {
		Log.d("", "onDoStartFailureInitModel()");
	}

	protected void onDoStartSuccessfullInitModel() {
		Log.d("", "onStartSuccessfullInit()");
	}

	protected void onDoStartInitModel() {
		Log.d("", "onDoStartInitModel()");
	}

	protected void doOnCreate(Bundle savedInstanceState) {
		Log.d("", "doOnCreate()");
	}

	protected void configView() {
		Log.d("", "configView()");
	}

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

	/**
	 * @return {@link ListView}
	 */
	protected abstract ListView findListView();

	/**
	 * @return {@link ArrayAdapter}
	 */
	protected abstract ArrayAdapter<E> makeListAdapter();

	/**
	 * @return {@link View}
	 */
	protected abstract View getMoreElementsView();

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
	// *** Private implementation
	// ********************************************

	private void populateListCompleted() {
		listView.invalidate();
		listAdapter.notifyDataSetChanged();
	}

	private void runInitModel() {
		ThreadUtil.execute(new Runnable() {
			@Override
			public void run() {
				model.init(context);
			}
		});
	}

	private void populateListView() {
		listAdapter.clear();
		for (E element : model.getElements()) {
			listAdapter.add(element);
		}
	}

	private void showProgressDialog() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog == null) {
					progressDialog = ApplicationDialogFactory.createProgressDialogForActivity(BaseListActivity.this);
				}
				progressDialog.show();
			}
		});
	}

	private void fillListView() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// populate list view with elements.
				populateListView();
				// update view.
				BaseListActivity.HANDLER.sendNotify(BaseListActivity.this);
				// enable view for search more elements.
				moreElementsView.setVisibility(View.VISIBLE);
			}
		});
	}

	private void hideProgressDialog() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog != null) {
					progressDialog.hide();
				}
			}
		});
	}

	// ********************************************
	// *** Inner-class
	// ********************************************

	/**
	 * @author Luciano Rey
	 */
	private static class CustomHandler extends Handler {

		/**
		 * @param activity
		 *            {@link BaseListActivity}
		 */
		public void sendNotify(BaseListActivity<?, ?> activity) {
			this.sendMessage(this.obtainMessage(1, activity));
		}

		/**
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(android.os.Message msg) {
			BaseListActivity<?, ?> activity = (BaseListActivity<?, ?>) msg.obj;
			activity.populateListCompleted();
		};

	}
}
