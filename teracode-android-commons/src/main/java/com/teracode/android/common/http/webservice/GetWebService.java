package com.teracode.android.common.http.webservice;

/**
 * @author Luciano Rey
 */
public interface GetWebService extends WebService {
	
	/**
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Object value);
	
}
