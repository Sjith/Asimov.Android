package com.teracode.android.demo.activity;

import android.content.Intent;
import android.view.View;

import com.teracode.android.demo.R;
import com.teracode.android.framework.activity.BaseActivity;
import com.teracode.android.framework.model.NullModel;

/**
 * @author Fernando Perez
 */
public class MainMenuActivity extends BaseActivity<NullModel> {

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return R.layout.main_menu_activity;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#createModel()
	 */
	@Override
	protected NullModel createModel() {
		return NullModel.getInstance();
	}

	/**
	 * @param view
	 *            {@link View}
	 */
	public void goListActivity(View view) {
		startActivity(new Intent(this, ListActivity.class));
	}

	/**
	 * @param view
	 *            {@link View}
	 */
	public void goMapActivity(View view) {
		startActivity(new Intent(this, MapActivity.class));
	}

	/**
	 * @param view
	 *            {@link View}
	 */
	public void goAsimovInfoActivity(View view) {
		startActivity(new Intent(this, AsimovInfoActivity.class));
	}
}