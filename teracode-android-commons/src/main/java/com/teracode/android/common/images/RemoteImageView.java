package com.teracode.android.common.images;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Luciano Rey
 */
public class RemoteImageView extends ImageView {
	
	protected String url;
	private int stubId;
	
	// **********************************************
	// *** Constructor's
	// **********************************************
	
	public RemoteImageView(Context context) {
		super(context);
	}
	
	public RemoteImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public RemoteImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	// **********************************************
	// ***
	// **********************************************
	
	public void setImageURL(String imageURL, int stubId) {
		this.stubId = stubId;
		if (imageURL != null) {
			this.setTag(imageURL);
			ImageLoader.get().displayImage(imageURL, this);
		} else {
			showStubImage();
		}
	}
	
	public void showStubImage() {
		this.setImageResource(stubId);
	}
}
