package com.teracode.android.common.http;

import org.apache.http.HttpResponse;

import android.util.Log;

import com.teracode.android.common.exception.code.CommonErrorCode;

/**
 * @author Luciano Rey
 */
public class HttpResponseValidatorImpl implements HttpResponseValidator {

	private static final int HTTP_200 = 200;
	private static final String REASON2 = " Reason: ";
	private static final String HTTP_ERROR_CODE = "HTTP Error code: ";
	private static final String HTTP_STATUS_CODE = "HTTP Status code: ";
	private static final String TAG = "HTTP response validator";
	
	private static HttpResponseValidatorImpl instance = new HttpResponseValidatorImpl();

	private HttpResponseValidatorImpl() {
		//nothing...
	}

	public static HttpResponseValidatorImpl getInstance() {
		return instance;
	}
	
	/**
	 * @see com.teracode.android.common.http.HttpResponseValidator#validate(org.apache.http.HttpResponse)
	 */
	@Override
	public void validate(HttpResponse httpResponse) {
		int code = httpResponse.getStatusLine().getStatusCode();
		if (code != HTTP_200) {
			String reason = httpResponse.getStatusLine().getReasonPhrase();
			Log.d(TAG, HTTP_ERROR_CODE + code + REASON2 + reason);
			throw CommonErrorCode.SERVER_ERROR.newBusinessException();
		}
		Log.d(TAG, HTTP_STATUS_CODE + code);
	}

}
