package com.teracode.android.common.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import com.teracode.android.common.http.webservice.PostWebService;

/**
 * @author Luciano Rey
 */
public class HttpPostWebService extends HttpWebService implements PostWebService {
	
	private HttpEntity entity;
	
	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 */
	private HttpPostWebService(HttpResponseValidator validator, String url) {
		super(validator, url);
	}
	
	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 * @return {@link HttpPostWebService}
	 */
	public static HttpPostWebService getNew(HttpResponseValidator validator, String url) {
		return new HttpPostWebService(validator, url);
	}
	
	/**
	 * @see com.teracode.android.common.http.HttpWebService#makeHttpUriRequest()
	 */
	@Override
	protected HttpUriRequest makeHttpUriRequest() throws URISyntaxException, UnsupportedEncodingException {
		// New post for send request.
		HttpPost post = new HttpPost(new URI(this.url));
		// set header values.
		for (NameValuePair pair : headers) {
			post.addHeader(pair.getName(), pair.getValue());
		}
		// set parameters for request.
		post.setEntity(entity);
		
		return post;
	}
	
	/**
	 * @see com.teracode.android.common.http.webservice.PostWebService#setBody(org.apache.http.HttpEntity)
	 */
	@Override
	public void setBody(HttpEntity entity) {
		this.entity = entity;
	}
}
