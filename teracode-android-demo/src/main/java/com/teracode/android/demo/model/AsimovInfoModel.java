package com.teracode.android.demo.model;

import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.demo.view.AsimovInfoView;
import com.teracode.android.framework.model.BaseModel;
import com.teracode.android.framework.model.ValueModel;

/**
 * @author Fernando Perez
 */
public class AsimovInfoModel extends BaseModel<AsimovInfoView> {

	/**
	 * @param view
	 */
	public AsimovInfoModel(AsimovInfoView view) {
		super(view);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseModel#doInit(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	protected void doInit(ValueModel valueModel) {
		ThreadUtil.sleep(6);
	}

}
