package com.teracode.android.common.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

/**
 * Factory class to create {@link HttpClient}s.
 * 
 * @author Luciano Rey
 */
public abstract class HttpClientFactory {
	
	// 10 seconds
	private static final int DEFAULT_TIMEOUT = 10000;
	// 60 seconds
	private static final int DEFAULT_SO_TIMEOUT = 60000;
	
	/**
	 * Creates a {@link DefaultHttpClient} and sets a timeout for it.
	 * 
	 * @return {@link DefaultHttpClient} The created client.
	 */
	public static DefaultHttpClient createDefaultHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_TIMEOUT);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_SO_TIMEOUT);
		return client;
	}
}
