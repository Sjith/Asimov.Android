package com.teracode.android.common.http;

import org.apache.http.HttpResponse;

/**
 * @author Luciano Rey
 */
public interface HttpResponseValidator {

	/**
	 * @param httpResponse {@link HttpResponse}
	 */
	public void validate(HttpResponse httpResponse);
}
