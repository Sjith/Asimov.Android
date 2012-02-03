package com.teracode.android.framework.view;

import com.teracode.android.common.exception.AndroidException;

/**
 * @author Fernando Perez
 */
public interface BaseView {

	/**
	 * 
	 */
	public void doStartInitModel();
	
	/**
	 * 
	 */
	public void doStartSuccessfullInitModel();
	
	/**
	 * @param exception
	 */
	public void doStartFailureInitModel(AndroidException exception);
	
}
