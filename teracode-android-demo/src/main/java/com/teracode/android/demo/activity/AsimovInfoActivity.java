package com.teracode.android.demo.activity;

import com.teracode.android.demo.R;
import com.teracode.android.demo.model.AsimovInfoModel;
import com.teracode.android.demo.view.AsimovInfoView;
import com.teracode.android.framework.activity.BaseActivity;

/**
 * @author Fernando Perez
 */
public class AsimovInfoActivity extends BaseActivity<AsimovInfoModel> implements AsimovInfoView {

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#createModel()
	 */
	@Override
	protected AsimovInfoModel createModel() {
		return new AsimovInfoModel(this);
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return R.layout.asimov_info_activity;
	}
}