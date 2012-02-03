package com.teracode.android.common.http;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import android.net.Uri;
import com.google.inject.internal.Lists;
import com.teracode.android.common.http.webservice.GetWebService;

/**
 * @author Luciano Rey
 */
public class HttpGetWebService extends HttpWebService implements GetWebService {
	
	/** 
	 * Parameter values of the request.
	 */
	private List<NameValuePair> parameters = Lists.newArrayList();
	
	/**
	 * @param validator
	 * @param url
	 */
	public HttpGetWebService(HttpResponseValidator validator, String url) {
		super(validator, url);
	}
	
	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 * @return {@link HttpGetWebService}
	 */
	public static HttpGetWebService getNew(HttpResponseValidator validator, String url) {
		return new HttpGetWebService(validator, url);
	}
	
	/**
	 * @see com.teracode.android.common.http.HttpWebService#makeHttpUriRequest()
	 */
	@Override
	protected HttpUriRequest makeHttpUriRequest() throws URISyntaxException, UnsupportedEncodingException {
		HttpGet httpGet = new HttpGet(this.url + makeStringParameters());
		for (NameValuePair pair : headers) {
			httpGet.addHeader(pair.getName(), pair.getValue());
		}
		
		return httpGet;
	}
	
	/**
	 * @see com.teracode.android.common.http.webservice.GetWebService#addParameter(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addParameter(String name, Object value) {
		parameters.add(new BasicNameValuePair(name, Uri.encode(value.toString())));
	}
	
	private String makeStringParameters() {
		String params = "";
		boolean isFirst = true;
		if (parameters.size() > 0) {
			for (NameValuePair pair : parameters) {
				if (isFirst) {
					params = params + "?" + pair.getName() + "=" + pair.getValue();
					isFirst = false;
				} else {
					params = params + "&" + pair.getName() + "=" + pair.getValue();
				}
			}
		}
		
		return params;
	}
}
