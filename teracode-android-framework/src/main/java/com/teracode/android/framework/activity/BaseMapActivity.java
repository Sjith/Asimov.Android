package com.teracode.android.framework.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboMapActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.inject.Inject;
import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.activity.context.ActivityLifeCycle;
import com.teracode.android.framework.activity.intent.AsimovIntent;
import com.teracode.android.framework.activity.intent.IntentContext;
import com.teracode.android.framework.dialog.ApplicationDialogFactory;
import com.teracode.android.framework.map.BalloonOverlayTapListener;
import com.teracode.android.framework.map.HolderOverlayItem;
import com.teracode.android.framework.map.ListBalloonItemizedOverlay;
import com.teracode.android.framework.map.MapPoint;
import com.teracode.android.framework.map.MapUtils;
import com.teracode.android.framework.model.BaseMapModel;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Luciano Rey
 */
public abstract class BaseMapActivity<T extends BaseMapModel<?>> extends RoboMapActivity implements AsimovActivity<T>,
		BalloonOverlayTapListener, BaseView {

	private T model;
	private IntentContext context;
	private MapView mapView;
	private Dialog progressDialog;
	@Inject
	private ActivityLifeCycle activityLifeCycle;

	/**
	 * @see roboguice.activity.GuiceMapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.configView();
		this.setContentView(getLayoutResourceId());

		this.context = getAsimovIntent().getContext();
		this.model = this.createModel();

		this.mapView = this.getMapView();
		this.mapView.setBuiltInZoomControls(true);

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
	// *** MapActivity
	// ********************************************

	/**
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	// ********************************************
	// *** Map listener
	// ********************************************

	/**
	 * @see com.teracode.android.framework.map.BalloonOverlayTapListener#onBallonItemTap(com.teracode.android.framework.map.HolderOverlayItem)
	 */
	@Override
	public void onBallonItemTap(HolderOverlayItem overlayItem) {
		this.model.onElementSelected(overlayItem.getData());
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
		this.displayItems();
		try {
			this.onDoStartSuccessfullInitModel();
		} finally {
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
	// *** AsimovActivity
	// ********************************************

	/**
	 * @see com.teracode.android.framework.activity.AsimovActivity#getModel()
	 */
	@Override
	public final T getModel() {
		return this.model;
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
		Log.d("", "onDoStartFailureInitModel()");
	}

	protected void onDoStartSuccessfullInitModel() {
		Log.d("", "onStartSuccessfullInit()");
	}

	protected void onDoStartInitModel() {
		Log.d("", "onDoStartInitModel()");
	}

	/**
	 * @param savedInstanceState
	 */
	protected void doOnCreate(Bundle savedInstanceState) {
		Log.d("AA", "doOnCreate()");
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
	 * @return
	 */
	protected abstract int getDrawableMapMarker();

	/**
	 * @return {@link MapView}
	 */
	protected abstract MapView getMapView();

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

	private void displayItems() {
		List<HolderOverlayItem> overlayItems = new ArrayList<HolderOverlayItem>();
		for (MapPoint mapPoint : model.getItems()) {
			GeoPoint point = MapUtils.createGeoPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
			overlayItems.add(new HolderOverlayItem(point, mapPoint.getTitle(), mapPoint.getSnippet(), mapPoint));
		}

		ListBalloonItemizedOverlay itemizedOverlay = new ListBalloonItemizedOverlay(mapView, this,
				this.getDrawableMapMarker(), overlayItems);
		mapView.getOverlays().add(itemizedOverlay);

		MapUtils.fitOvelays(mapView, itemizedOverlay);
	}

	private void showProgressDialog() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog == null) {
					progressDialog = ApplicationDialogFactory.createProgressDialogForActivity(BaseMapActivity.this);
				}
				progressDialog.show();
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

}
