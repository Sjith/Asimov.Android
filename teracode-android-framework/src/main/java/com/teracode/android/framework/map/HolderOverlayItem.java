package com.teracode.android.framework.map;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * 
 * @author Maxi Rosson
 * @param <T>
 */
public class HolderOverlayItem extends OverlayItem {
	
	private MapPoint data;
	
	/**
	 * @param point
	 * @param title
	 * @param snippet
	 * @param data
	 */
	public HolderOverlayItem(GeoPoint point, String title, String snippet, MapPoint data) {
		super(point, title, snippet);
		this.data = data;
	}
	
	/**
	 * @param point
	 * @param title
	 * @param data
	 */
	public HolderOverlayItem(GeoPoint point, String title, MapPoint data) {
		this(point, title, null, data);
	}
	
	/**
	 * @param point
	 * @param data
	 */
	public HolderOverlayItem(GeoPoint point, MapPoint data) {
		this(point, null, null, data);
	}
	
	/**
	 * @return the data
	 */
	public MapPoint getData() {
		return data;
	}
	
}
