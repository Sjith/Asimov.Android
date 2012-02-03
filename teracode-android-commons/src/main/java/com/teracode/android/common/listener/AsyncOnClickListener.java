package com.teracode.android.common.listener;

import com.teracode.android.common.util.ThreadUtil;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Luciano Rey
 */
public abstract class AsyncOnClickListener implements OnClickListener {

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public final void onClick(final View view) {
		ThreadUtil.execute(new Runnable() {
			/**
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				onAsyncRun(view);
			}
		});
	}

	/**
	 * Called when a view has been clicked.
	 * 
	 * @param view The view that was clicked.
	 */
	protected abstract void onAsyncRun(View view);
	
}
