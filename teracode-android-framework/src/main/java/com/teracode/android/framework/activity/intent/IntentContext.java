package com.teracode.android.framework.activity.intent;

import java.util.Map;

import com.google.inject.internal.Maps;
import com.teracode.android.framework.model.ValueModel;

/**
 * @author Luciano Rey
 */
public class IntentContext implements ValueModel {

	private static Map<String, Object> values = Maps.newHashMap();
	
	private IntentContext() {
		//nothing...
	}
	
	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public static IntentContext newContext(String key, Object value) {
		IntentContext.values.clear();
		IntentContext.values.put(key, value);
		return new IntentContext();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		IntentContext.values.put(key, value);
	}
	
	/**
	 * @see com.teracode.android.framework.model.ValueModel#get(java.lang.String)
	 */
	public Object get(String key) {
		return IntentContext.values.get(key);
	}
	
}
