package com.teracode.android.demo.activity;

import com.google.android.maps.MapView;
import com.teracode.android.demo.model.MapModel;
import com.teracode.android.framework.R;
import com.teracode.android.framework.activity.BaseMapActivity;

/**
 * @author Luciano Rey
 */
public class MapActivity extends BaseMapActivity<MapModel> {

	/**
	 * @see com.teracode.android.framework.activity.BaseMapActivity#createModel()
	 */
	@Override
	protected MapModel createModel() {
		return new MapModel(this);
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseMapActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return R.layout.simple_map_activity;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseMapActivity#getDrawableMapMarker()
	 */
	@Override
	protected int getDrawableMapMarker() {
		return R.drawable.map_marker;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseMapActivity#getMapView()
	 */
	@Override
	protected MapView getMapView() {
		return (MapView) this.findViewById(R.id.mapview);
	}

}
