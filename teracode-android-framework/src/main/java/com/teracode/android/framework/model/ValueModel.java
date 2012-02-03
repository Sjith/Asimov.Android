package com.teracode.android.framework.model;

import java.io.Serializable;

/**
 * @author Luciano Rey
 */
public interface ValueModel extends Serializable {

	/**
	 * @param key
	 * @return a {@link Object}
	 */
	public Object get(String key);
	
}
