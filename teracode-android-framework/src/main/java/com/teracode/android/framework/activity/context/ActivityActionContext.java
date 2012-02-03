package com.teracode.android.framework.activity.context;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;

public interface ActivityActionContext {

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo);
	
	public boolean onMenuItemSelected(int featureId, MenuItem item);
}