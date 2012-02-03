package com.teracode.android.framework.map;

import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.inject.internal.Lists;
import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Maxi Rosson
 */
public class ListBalloonItemizedOverlay extends BalloonItemizedOverlay<HolderOverlayItem> {
	
	private BalloonOverlayTapListener tapListener;
	private List<HolderOverlayItem> overlayItems = Lists.newArrayList();
	
	/**
	 * @param mapView
	 * @param tapListener
	 * @param defaultMarkerId The id of the {@link Drawable} to be used as marker
	 * @param overlayItems
	 */
	public ListBalloonItemizedOverlay(MapView mapView, BalloonOverlayTapListener tapListener, int defaultMarkerId,
			List<HolderOverlayItem> overlayItems) {
		this(mapView, tapListener, defaultMarkerId);
		this.overlayItems = overlayItems;
		populate();
	}
	
	/**
	 * @param mapView
	 * @param tapListener
	 * @param defaultMarkerId The id of the {@link Drawable} to be used as marker
	 */
	public ListBalloonItemizedOverlay(MapView mapView, BalloonOverlayTapListener tapListener, int defaultMarkerId) {
		this(mapView, tapListener, ApplicationProvider.get().getActiveApplication().getResources().getDrawable(defaultMarkerId));
	}
	
	/**
	 * @param mapView
	 * @param tapListener
	 * @param defaultMarker The {@link Drawable} to be used as marker
	 */
	public ListBalloonItemizedOverlay(MapView mapView, BalloonOverlayTapListener tapListener, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker), mapView);
		this.tapListener = tapListener;
	}
	
	public void addItem(HolderOverlayItem overlayItem) {
		overlayItems.add(overlayItem);
		populate();
	}
	
	/**
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected HolderOverlayItem createItem(int index) {
		return overlayItems.get(index);
	}
	
	/**
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return overlayItems.size();
	}
	
	/**
	 * @see com.teracode.android.common.map.BalloonItemizedOverlay#onBalloonTap(int)
	 */
	@Override
	protected void onBalloonTap(int index) {
		HolderOverlayItem item = overlayItems.get(index);
		tapListener.onBallonItemTap(item);
		super.onTap(index);
	}
}
