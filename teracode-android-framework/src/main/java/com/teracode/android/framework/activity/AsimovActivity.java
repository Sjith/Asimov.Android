package com.teracode.android.framework.activity;

import com.teracode.android.framework.model.Model;

/**
 * @author Fernando Perez
 */
public interface AsimovActivity<T extends Model> {

	/**
	 * @return model.
	 */
	public T getModel();
	
}
