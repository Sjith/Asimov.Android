package com.teracode.android.framework.activity.context;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;

/**
 * @author Fernando Perez
 *
 */
public class ActivityActionContextImp implements ActivityActionContext {

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityActionContext#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	}

	/**
	 * @see com.teracode.android.framework.activity.context.ActivityActionContext#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return false;
	}

}
