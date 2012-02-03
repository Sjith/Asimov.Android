package com.teracode.android.framework.view;

import com.teracode.android.common.exception.AndroidException;

/**
 * @author Luciano Rey
 */
public interface BaseListView extends BaseView {

	public void doStartLoadMoreElements();

	public void doStartSuccessfullLoadMoreElements();

	/**
	 * @param exception
	 */
	public void doStartFailureLoadMoreElements(AndroidException exception);

}
