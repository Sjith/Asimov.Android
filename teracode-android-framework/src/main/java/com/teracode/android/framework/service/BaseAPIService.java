package com.teracode.android.framework.service;

import com.teracode.android.common.http.HttpResponseValidator;
import com.teracode.android.common.http.HttpResponseValidatorImpl;
import com.teracode.android.common.http.WebServiceFactory;
import com.teracode.android.common.http.parser.WebServiceParser;
import com.teracode.android.common.http.webservice.GetWebService;
import com.teracode.android.common.http.webservice.PostWebService;
import com.teracode.android.common.http.webservice.WebService;

/**
 * @author Luciano Rey
 */
public abstract class BaseAPIService {

	/**
	 * @param webService
	 * @param parser
	 * @return {@link Object}
	 */
	protected Object execute(WebService webService, WebServiceParser parser) {
		return webService.execute(parser);
	}
	
	/**
	 * @param url
	 * @return {@link GetWebService}
	 */
	protected GetWebService newGetService(String url) {
		return this.newGetService(url, HttpResponseValidatorImpl.getInstance());
	}

	/**
	 * @param url
	 * @param validator {@link HttpResponseValidator}
	 * @return {@link GetWebService}
	 */
	protected GetWebService newGetService(String url, HttpResponseValidator validator) {
		return WebServiceFactory.newGet(validator, url);
	}
	
	/**
	 * @param url
	 * @return {@link PostWebService}
	 */
	protected PostWebService newPostService(String url) {
		return this.newPostService(url, HttpResponseValidatorImpl.getInstance());
	}
	
	/**
	 * @param url
	 * @param validator {@link HttpResponseValidator}
	 * @return {@link PostWebService}
	 */
	protected PostWebService newPostService(String url, HttpResponseValidator validator) {
		return WebServiceFactory.newPost(validator, url);
	}	
	
}
