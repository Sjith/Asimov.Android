package com.teracode.android.framework.view;

import com.teracode.android.common.exception.AndroidException;

/**
 * @author Fernando Perez
 */
public class NullView implements BaseView {

	private static NullView instance = new NullView();

	private NullView() {
		//nothing...
	}

	public static NullView getInstance() {
		return instance;
	}
	
	@Override
	public void doStartInitModel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doStartSuccessfullInitModel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doStartFailureInitModel(AndroidException exception) {
		// TODO Auto-generated method stub
	}

}
