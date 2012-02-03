package com.teracode.android.common.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.google.inject.internal.Lists;
import com.teracode.android.common.exception.code.CommonErrorCode;
import com.teracode.android.common.http.cookie.CookieRepository;
import com.teracode.android.common.http.cookie.CookieRepositoryImpl;
import com.teracode.android.common.http.parser.WebServiceParser;
import com.teracode.android.common.http.webservice.WebService;

/**
 * @author Luciano Rey
 */
public abstract class HttpWebService implements WebService {

	private static final String TAG = HttpWebService.class.getSimpleName();

	/**
	 * Base URL of the request to execute
	 */
	protected String url;
	/**
	 * Header values of the request.
	 */
	protected List<NameValuePair> headers = Lists.newArrayList();
	/**
	 * The repository to handle the http client's cookies
	 */
	private final CookieRepository cookieRepository = CookieRepositoryImpl.getInstance();
	/**
	 * Response validor.
	 */
	private final HttpResponseValidator validator;

	/**
	 * @param validator {@link HttpResponseValidator}
	 * @param url
	 */
	public HttpWebService(HttpResponseValidator validator, String url) {
		this.url = url;
		this.validator = validator;
	}

	/**
	 * @see com.teracode.android.common.http.webservice.WebService#execute(com.teracode.android.common.http.parser.WebServiceParser)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute(WebServiceParser parser) {
		DefaultHttpClient client = null;
		try {
			// make client for http.
			client = HttpClientFactory.createDefaultHttpClient();

			if (saveCookies()) {
				// Add all the cookies to the http client
				for (Cookie cookie : cookieRepository.getAll()) {
					client.getCookieStore().addCookie(cookie);
				}
			}

			// make request.
			HttpUriRequest request = this.makeHttpUriRequest();

			// log info.
			Log.d(TAG, "Request: " + request.getRequestLine().getUri());

			// execute request.
			final HttpResponse httpResponse = client.execute(request);

			if (saveCookies()) {
				// Save all returned cookies into the repository.
				cookieRepository.saveAll(client.getCookieStore().getCookies());
			}

			// validate response.
			this.validator.validate(httpResponse);
			// obtain input stream.
			final InputStream inputStream = httpResponse.getEntity().getContent();
			// parse and return response.
			return (T) (parser != null ? parser.parse(inputStream) : null);

		} catch (URISyntaxException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
		} catch (ClientProtocolException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
		} catch (IOException e) {
			throw CommonErrorCode.CONNECTION_ERROR.newApplicationException(e);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}

	/**
	 * @see com.teracode.android.common.http.webservice.WebService#addHeader(java.lang.String, java.lang.String)
	 */
	@Override
	public void addHeader(String name, String value) {
		headers.add(new BasicNameValuePair(name, value));
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " - " + this.url;
	}

	/**
	 * Create the {@link HttpUriRequest} to send. It can be a GET or a POST.
	 */
	protected abstract HttpUriRequest makeHttpUriRequest() throws URISyntaxException, UnsupportedEncodingException;

	/**
	 * @return true if save cookies or false in the other case.
	 */
	protected Boolean saveCookies() {
		return false;
	}

}
