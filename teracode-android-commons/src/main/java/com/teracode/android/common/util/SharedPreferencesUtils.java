package com.teracode.android.common.util;

import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.teracode.android.common.application.AbstractApplication;
import com.teracode.android.common.application.ApplicationProvider;

/**
 * Utils to work with the shared preferences
 * 
 * @author Maxi Rosson
 */
public class SharedPreferencesUtils {
	
	/**
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, String value) {
		Editor editor = getEditor();
		editor.putString(key, value);
		
		// Commit the edits!
		editor.commit();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, Boolean value) {
		Editor editor = getEditor();
		editor.putBoolean(key, value);
		
		// Commit the edits!
		editor.commit();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, Integer value) {
		Editor editor = getEditor();
		editor.putInt(key, value);
		
		// Commit the edits!
		editor.commit();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, Long value) {
		Editor editor = getEditor();
		editor.putLong(key, value);
		
		// Commit the edits!
		editor.commit();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, Float value) {
		Editor editor = getEditor();
		editor.putFloat(key, value);
		
		// Commit the edits!
		editor.commit();
	}
	
	/**
	 * @return {@link Editor}
	 */
	public static Editor getEditor() {
		SharedPreferences settings = getSharedPreferences();
		return settings.edit();
	}
	
	
	/**
	 * Retrieves all the existent shared preferences.
	 * 
	 * @return The shared preferences.
	 */
	public static Map<String, ?> loadAllPreferences() {
		return getSharedPreferences().getAll();
	}
	
	/**
	 * Retrieve a string value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @param defaultValue Value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue.
	 */
	public static String loadPreference(String key, String defaultValue) {
		return getSharedPreferences().getString(key, defaultValue);
	}
	
	/**
	 * Retrieve a string value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @return the preference value if it exists, or null.
	 */
	public static String loadPreference(String key) {
		return getSharedPreferences().getString(key, (String)null);
	}
	
	/**
	 * Retrieve a boolean value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @param defaultValue Value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue.
	 */
	public static Boolean loadPreferenceAsBoolean(String key, Boolean defaultValue) {
		return getSharedPreferences().getBoolean(key, defaultValue);
	}
	
	/**
	 * Retrieve a long value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @param defaultValue Value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue.
	 */
	public static Long loadPreferenceAsLong(String key, Long defaultValue) {
		return getSharedPreferences().getLong(key, defaultValue);
	}
	
	/**
	 * Retrieve a long value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @return the preference value if it exists, or 0L.
	 */
	public static Long loadPreferenceAsLong(String key) {
		return getSharedPreferences().getLong(key, 0L);
	}
	
	/**
	 * Retrieve an Integer value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @param defaultValue Value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue.
	 */
	public static Integer loadPreferenceAsInteger(String key, Integer defaultValue) {
		return getSharedPreferences().getInt(key, defaultValue);
	}
	
	/**
	 * Retrieve an Integer value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @return the preference value if it exists, or 0.
	 */
	public static Integer loadPreferenceAsInteger(String key) {
		return getSharedPreferences().getInt(key, 0);
	}
	
	/**
	 * Retrieve a Float value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @param defaultValue Value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue.
	 */
	public static Float loadPreferenceAsFloat(String key, Float defaultValue) {
		return getSharedPreferences().getFloat(key, defaultValue);
	}
	
	/**
	 * Retrieve a Float value from the preferences.
	 * 
	 * @param key The name of the preference to retrieve
	 * @return the preference value if it exists, or 0F.
	 */
	public static Float loadPreferenceAsFloat(String key) {
		return getSharedPreferences().getFloat(key, 0F);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static boolean hasPreference(String key) {
		return getSharedPreferences().contains(key);
	}
	
	/**
	 * @param key
	 */
	public static void removePreference(String key) {
		Editor editor = getEditor();
		editor.remove(key);
		
		// Commit the edits!
		editor.commit();
	}
	
	
	/**
	 * @return {@link SharedPreferences}
	 */
	private static SharedPreferences getSharedPreferences() {
		AbstractApplication application = ApplicationProvider.get().getActiveApplication();
		return application.getSharedPreferences(application.getPackageName(), 0);
	}
	
}
