package com.teracode.android.framework.map;

/**
 * @author Luciano Rey
 */
public class SimpleMapPoint implements MapPoint {

	private final String title;
	private final String snippet;
	private final Double latitude;
	private final Double longitude;

	/**
	 * @param title
	 * @param snippet
	 * @param latitude
	 * @param longitude
	 */
	public SimpleMapPoint(String title, String snippet, Double latitude, Double longitude) {
		this.title = title;
		this.snippet = snippet;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @see com.teracode.android.framework.map.MapPoint#getLatitude()
	 */
	@Override
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * @see com.teracode.android.framework.map.MapPoint#getLongitude()
	 */
	@Override
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * @see com.teracode.android.framework.map.MapPoint#getTitle()
	 */
	@Override
	public String getTitle() {
		return this.title;
	}

	/**
	 * @see com.teracode.android.framework.map.MapPoint#getSnippet()
	 */
	@Override
	public String getSnippet() {
		return this.snippet;
	}

}
