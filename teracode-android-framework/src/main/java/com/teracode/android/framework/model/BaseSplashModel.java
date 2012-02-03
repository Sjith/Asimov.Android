package com.teracode.android.framework.model;

import com.teracode.android.framework.view.BaseView;

/**
 * @author Fernando Perez
 */
public abstract class BaseSplashModel<T extends BaseView> extends BaseModel<T> {

	private long start;
	private Long duration;
	
	/**
	 * @param view
	 */
	public BaseSplashModel(T view) {
		super(view);
		this.start = System.currentTimeMillis();
	}

	public long getMinDisplayMiliSeconds() {
		return (int) (2.5 * 1000);
	}

	public long getDuration() {
		if(this.duration == null) {
			this.duration = System.currentTimeMillis() - start; 
		}
		return this.duration;
	}

	public boolean hasExpiredTime() {
		return (getDuration() < getMinDisplayMiliSeconds());
	}
	
	public long getTimeSleep() {
		return getMinDisplayMiliSeconds() - getDuration();
	}

}
