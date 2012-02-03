package com.teracode.android.framework.model;

import java.util.List;

import com.google.inject.internal.Lists;
import com.teracode.android.framework.map.MapPoint;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Luciano Rey
 */
public abstract class BaseMapModel<T extends BaseView> extends BaseModel<T> {

	private final List<MapPoint> items = Lists.newArrayList();

	/**
	 * @param view
	 */
	public BaseMapModel(T view) {
		super(view);
	}

	/**
	 * @return {@link List}
	 */
	public List<MapPoint> getItems() {
		return this.items;
	}

	/**
	 * @param mapPoint
	 *            {@link MapPoint}
	 */
	public void addMapPoint(MapPoint mapPoint) {
		this.items.add(mapPoint);
	}

	/**
	 * @param mapPoint
	 *            {@link MapPoint}
	 */
	public final void onElementSelected(MapPoint mapPoint) {
		this.handleElementSelected(mapPoint);
	}

	// ********************************************
	// *** Abstract method's
	// ********************************************

	/**
	 * @param mapPoint
	 *            {@link MapPoint}
	 */
	protected abstract void handleElementSelected(MapPoint mapPoint);

}
