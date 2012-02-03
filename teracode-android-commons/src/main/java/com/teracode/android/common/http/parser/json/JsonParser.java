package com.teracode.android.common.http.parser.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.json.JSONException;

import android.util.Log;

import com.teracode.android.common.exception.code.CommonErrorCode;
import com.teracode.android.common.http.parser.WebServiceParser;
import com.teracode.android.common.util.DateUtil;

/**
 * JSON input streams parser
 * 
 * @author Luciano Rey
 */
public abstract class JsonParser implements WebServiceParser {
	
	private static final String TAG = JsonParser.class.getSimpleName();
	
	/**
	 * @see com.teracode.android.common.http.parser.WebServiceParser#parse(java.io.InputStream)
	 */
	@Override
	public Object parse(InputStream inputStream) {
		Date begin = DateUtil.now();
		try {
			// Read the JSON response
			String result = convertStreamToString(inputStream);
			
			// Create a wrapped JSONObject
			JsonObjectWrapper json = null;
			if (result.equals("[]\n")) {
				json = new JsonObjectWrapper();
			} else {
				json = new JsonObjectWrapper(result);
			}
			
			// Parse the JSONObject
			return parse(json);
		} catch (JSONException e) {
			throw CommonErrorCode.SERVER_ERROR.newApplicationException(e);
		} finally {
			Log.i(TAG, "Begin: " + begin + " End: " + DateUtil.now());
		}
	}
	
	/**
	 * @param json
	 * @return The parsed object
	 * @throws JSONException
	 */
	protected abstract Object parse(JsonObjectWrapper json) throws JSONException;
	
	/**
	 * @param is {@link InputStream}
	 * @return string
	 */
	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
			}
		}
		return sb.toString();
	}
	
}
