package com.teracode.android.demo.model;

import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.model.BaseSplashModel;
import com.teracode.android.framework.model.ValueModel;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Fernando Perez
 */
public class SplashModel extends BaseSplashModel<BaseView> {

	/**
	 * @param view
	 */
	public SplashModel(BaseView view) {
		super(view);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseSplashModel#getMinDisplayMiliSeconds()
	 */
	@Override
	public long getMinDisplayMiliSeconds() {
		return (int) (4 * 1000);
	}
	
	/**
	 * @see com.teracode.android.framework.model.BaseModel#doInit(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	protected void doInit(ValueModel valueModel) {
		ThreadUtil.sleep(5);
	}
}
