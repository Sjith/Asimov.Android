package com.teracode.android.common.http.parser.json;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Maxi Rosson
 */
public class JsonObjectWrapper {
	
	private JSONObject jsonObject;
	
	public JsonObjectWrapper() {
		this(new JSONObject());
	}
	
	public JsonObjectWrapper(String string) throws JSONException {
		this(new JSONObject(string));
	}
	
	public JsonObjectWrapper(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return jsonObject.equals(o);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return jsonObject.hashCode();
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#accumulate(java.lang.String, java.lang.Object)
	 */
	public JsonObjectWrapper accumulate(String key, Object value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.accumulate(key, value));
	}
	
	/**
	 * @param key
	 * @return the {@link Object}
	 * @throws JSONException
	 * @see org.json.JSONObject#get(java.lang.String)
	 */
	public Object get(String key) throws JSONException {
		return jsonObject.get(key);
	}
	
	/**
	 * @param key
	 * @return the boolean
	 * @throws JSONException
	 * @see org.json.JSONObject#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String key) throws JSONException {
		return jsonObject.getBoolean(key);
	}
	
	/**
	 * @param key
	 * @return the double
	 * @throws JSONException
	 * @see org.json.JSONObject#getDouble(java.lang.String)
	 */
	public double getDouble(String key) throws JSONException {
		return jsonObject.getDouble(key);
	}
	
	/**
	 * @param key
	 * @return the int
	 * @throws JSONException
	 * @see org.json.JSONObject#getInt(java.lang.String)
	 */
	public int getInt(String key) throws JSONException {
		return jsonObject.getInt(key);
	}
	
	/**
	 * @param key
	 * @return the {@link JsonArrayWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#getJSONArray(java.lang.String)
	 */
	public JsonArrayWrapper getJSONArray(String key) throws JSONException {
		return new JsonArrayWrapper(jsonObject.getJSONArray(key));
	}
	
	/**
	 * @param key
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#getJSONObject(java.lang.String)
	 */
	public JsonObjectWrapper getJSONObject(String key) throws JSONException {
		return new JsonObjectWrapper(jsonObject.getJSONObject(key));
	}
	
	/**
	 * @param key
	 * @return the long
	 * @throws JSONException
	 * @see org.json.JSONObject#getLong(java.lang.String)
	 */
	public long getLong(String key) throws JSONException {
		return jsonObject.getLong(key);
	}
	
	/**
	 * @param key
	 * @return the {@link String}
	 * @throws JSONException
	 * @see org.json.JSONObject#getString(java.lang.String)
	 */
	public String getString(String key) throws JSONException {
		String value = null;
		if (!jsonObject.isNull(key)) {
			value = jsonObject.getString(key);
		}
		return value;
	}
	
	/**
	 * @param key
	 * @return the boolean
	 * @see org.json.JSONObject#has(java.lang.String)
	 */
	public boolean has(String key) {
		return jsonObject.has(key);
	}
	
	/**
	 * @param key
	 * @return the boolean
	 * @see org.json.JSONObject#isNull(java.lang.String)
	 */
	public boolean isNull(String key) {
		return jsonObject.isNull(key);
	}
	
	/**
	 * @return the {@link Iterator}
	 * @see org.json.JSONObject#keys()
	 */
	public Iterator<?> keys() {
		return jsonObject.keys();
	}
	
	/**
	 * @return the int
	 * @see org.json.JSONObject#length()
	 */
	public int length() {
		return jsonObject.length();
	}
	
	/**
	 * @return the {@link JsonArrayWrapper}
	 * @see org.json.JSONObject#names()
	 */
	public JsonArrayWrapper names() {
		return new JsonArrayWrapper(jsonObject.names());
	}
	
	/**
	 * @param key
	 * @return the {@link Object}
	 * @see org.json.JSONObject#opt(java.lang.String)
	 */
	public Object opt(String key) {
		return jsonObject.opt(key);
	}
	
	/**
	 * @param key
	 * @return the boolean
	 * @see org.json.JSONObject#optBoolean(java.lang.String)
	 */
	public boolean optBoolean(String key) {
		return jsonObject.optBoolean(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return the boolean
	 * @see org.json.JSONObject#optBoolean(java.lang.String, boolean)
	 */
	public boolean optBoolean(String key, boolean defaultValue) {
		return jsonObject.optBoolean(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @return the double
	 * @see org.json.JSONObject#optDouble(java.lang.String)
	 */
	public double optDouble(String key) {
		return jsonObject.optDouble(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return the double
	 * @see org.json.JSONObject#optDouble(java.lang.String, double)
	 */
	public double optDouble(String key, double defaultValue) {
		return jsonObject.optDouble(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @return the int
	 * @see org.json.JSONObject#optInt(java.lang.String)
	 */
	public int optInt(String key) {
		return jsonObject.optInt(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return the int
	 * @see org.json.JSONObject#optInt(java.lang.String, int)
	 */
	public int optInt(String key, int defaultValue) {
		return jsonObject.optInt(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @return the {@link JsonArrayWrapper}
	 * @see org.json.JSONObject#optJSONArray(java.lang.String)
	 */
	public JsonArrayWrapper optJSONArray(String key) {
		return new JsonArrayWrapper(jsonObject.optJSONArray(key));
	}
	
	/**
	 * @param key
	 * @return the {@link JsonObjectWrapper}
	 * @see org.json.JSONObject#optJSONObject(java.lang.String)
	 */
	public JsonObjectWrapper optJSONObject(String key) {
		return new JsonObjectWrapper(jsonObject.optJSONObject(key));
	}
	
	/**
	 * @param key
	 * @return the long
	 * @see org.json.JSONObject#optLong(java.lang.String)
	 */
	public long optLong(String key) {
		return jsonObject.optLong(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return the long
	 * @see org.json.JSONObject#optLong(java.lang.String, long)
	 */
	public long optLong(String key, long defaultValue) {
		return jsonObject.optLong(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @return the {@link String}
	 * @see org.json.JSONObject#optString(java.lang.String)
	 */
	public String optString(String key) {
		return jsonObject.optString(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return the {@link String}
	 * @see org.json.JSONObject#optString(java.lang.String, java.lang.String)
	 */
	public String optString(String key, String defaultValue) {
		return jsonObject.optString(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, boolean)
	 */
	public JsonObjectWrapper put(String key, boolean value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.put(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, double)
	 */
	public JsonObjectWrapper put(String key, double value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.put(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, int)
	 */
	public JsonObjectWrapper put(String key, int value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.put(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, long)
	 */
	public JsonObjectWrapper put(String key, long value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.put(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, java.lang.Object)
	 */
	public JsonObjectWrapper put(String key, Object value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.put(key, value));
	}
	
	/**
	 * @param key
	 * @param value
	 * @return the {@link JsonObjectWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#putOpt(java.lang.String, java.lang.Object)
	 */
	public JsonObjectWrapper putOpt(String key, Object value) throws JSONException {
		return new JsonObjectWrapper(jsonObject.putOpt(key, value));
	}
	
	/**
	 * @param key
	 * @return the {@link Object}
	 * @see org.json.JSONObject#remove(java.lang.String)
	 */
	public Object remove(String key) {
		return jsonObject.remove(key);
	}
	
	/**
	 * @param names
	 * @return the {@link JsonArrayWrapper}
	 * @throws JSONException
	 * @see org.json.JSONObject#toJSONArray(org.json.JSONArray)
	 */
	public JsonArrayWrapper toJSONArray(JSONArray names) throws JSONException {
		return new JsonArrayWrapper(jsonObject.toJSONArray(names));
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return jsonObject.toString();
	}
	
	/**
	 * @param indentFactor
	 * @return the {@link String}
	 * @throws JSONException
	 * @see org.json.JSONObject#toString(int)
	 */
	public String toString(int indentFactor) throws JSONException {
		return jsonObject.toString(indentFactor);
	}
	
}
