package com.teracode.android.framework.model;

import com.teracode.android.framework.view.NullView;

/**
 * @author Fernando Perez
 */
public final class NullModel extends BaseModel<NullView> {

	private static NullModel instance = new NullModel();

	private NullModel() {
		super(NullView.getInstance());
	}

	public static NullModel getInstance() {
		return instance;
	}
	
	/**
	 * @see com.teracode.android.framework.model.BaseModel#doInit(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	protected void doInit(ValueModel valueModel) {
		// TODO Auto-generated method stub
	}

}
