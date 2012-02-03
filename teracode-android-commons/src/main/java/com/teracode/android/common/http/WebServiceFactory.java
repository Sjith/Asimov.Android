package com.teracode.android.common.http;

import com.teracode.android.common.http.webservice.GetWebService;
import com.teracode.android.common.http.webservice.PostWebService;

/**
 * @author Luciano Rey
 */
public class WebServiceFactory {
	
	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 * @return {@link GetWebService}
	 */
	public static GetWebService newGet(HttpResponseValidator validator, String url) {
		return HttpGetWebService.getNew(validator, url);
	}
	
	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 * @return {@link PostWebService}
	 */
	public static PostWebService newPost(HttpResponseValidator validator, String url) {
		return HttpPostWebService.getNew(validator, url);
	}
	
}
