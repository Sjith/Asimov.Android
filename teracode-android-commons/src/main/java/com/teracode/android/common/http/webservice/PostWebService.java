package com.teracode.android.common.http.webservice;

import org.apache.http.HttpEntity;

/**
 * @author Luciano Rey
 */
public interface PostWebService extends WebService {
	
	/**
	 * @param entity {@link HttpEntity}
	 */
	public void setBody(HttpEntity entity);
	
}
