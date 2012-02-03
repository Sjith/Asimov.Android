package com.teracode.android.common.http.webservice;

import com.teracode.android.common.http.parser.WebServiceParser;

/**
 * @author Luciano Rey
 */
public interface WebService {
	
	/**
	 * @param <T>
	 * @param parser
	 * @return WebServiceResponse
	 */
	public <T> T execute(WebServiceParser parser);
	
	/**
	 * @param name
	 * @param value
	 */
	public void addHeader(String name, String value);
	
}
