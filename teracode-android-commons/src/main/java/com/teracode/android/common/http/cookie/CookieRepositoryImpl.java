package com.teracode.android.common.http.cookie;

import java.util.List;
import java.util.Map.Entry;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import android.util.Log;

import com.google.inject.internal.Lists;
import com.teracode.android.common.util.DateUtil;
import com.teracode.android.common.util.SharedPreferencesUtils;

/**
 * {@link CookieRepository} implementation.
 * 
 * TODO: We should cache all cookies in memory to avoid retrieving them for each API call.
 * 
 * @author Estefania Caravatti
 */
public class CookieRepositoryImpl implements CookieRepository {
	
	private static final String TAG = CookieRepositoryImpl.class.getSimpleName();
	
	private static final String NAME = ".cookie.name";
	private static final String VALUE = ".cookie.value";
	private static final String DOMAIN = ".cookie.domain";
	private static final String EXPIRY_DATE = ".cookie.expirationdate";
	
	// TODO: Use Guice to inject this repository into other components.
	private static final CookieRepositoryImpl instance = new CookieRepositoryImpl();
	
	/**
	 * Singleton constructor.
	 */
	private CookieRepositoryImpl() {
	}
	
	/**
	 * @see com.teracode.common.cookie.CookieRepository#save(org.apache.http.cookie.Cookie)
	 */
	@Override
	public void save(Cookie cookie) {
		String cookieName = cookie.getName();
		Log.d(TAG, "Saving cookie " + cookieName + "[" + cookie.getValue() + "]");
		
		SharedPreferencesUtils.savePreference(generateCookieNameKey(cookieName), cookieName);
		SharedPreferencesUtils.savePreference(generateCookieValueKey(cookieName), cookie.getValue());
		SharedPreferencesUtils.savePreference(generateCookieDomainKey(cookieName), cookie.getDomain());
		if (cookie.getExpiryDate() != null) {
			SharedPreferencesUtils.savePreference(generateCookieExiryDateKey(cookieName),
				DateUtil.unTransform(cookie.getExpiryDate()));
		}
	}
	
	/**
	 * @see com.teracode.common.cookie.CookieRepository#saveAll(java.util.List)
	 */
	@Override
	public void saveAll(List<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			save(cookie);
		}
	}
	
	/**
	 * @see com.teracode.common.cookie.CookieRepository#remove(org.apache.http.cookie.Cookie)
	 */
	@Override
	public void remove(Cookie cookie) {
		String cookieName = cookie.getName();
		remove(cookieName);
	}
	
	/**
	 * Removes the {@link Cookie} with the given name.
	 * 
	 * @param cookieName The {@link Cookie}'s name.
	 */
	private void remove(String cookieName) {
		Log.d(TAG, "Removing cookie " + cookieName);
		
		SharedPreferencesUtils.removePreference(generateCookieNameKey(cookieName));
		SharedPreferencesUtils.removePreference(generateCookieValueKey(cookieName));
		SharedPreferencesUtils.removePreference(generateCookieDomainKey(cookieName));
		SharedPreferencesUtils.removePreference(generateCookieExiryDateKey(cookieName));
	}
	
	/**
	 * @see com.teracode.common.cookie.CookieRepository#removeAll()
	 */
	@Override
	public void removeAll() {
		for (Entry<String, ?> entry : SharedPreferencesUtils.loadAllPreferences().entrySet()) {
			if (entry.getKey().endsWith(NAME)) {
				remove(String.class.cast(entry.getValue()));
			}
		}
	}
	
	/**
	 * Gets a {@link Cookie} from the repository if it exists.
	 * 
	 * @param cookieName The name of the {@link Cookie} to get.
	 * @return The {@link Cookie}.
	 */
	private Cookie get(String cookieName) {
		BasicClientCookie cookie = new BasicClientCookie(cookieName,
				SharedPreferencesUtils.loadPreference(generateCookieValueKey(cookieName)));
		cookie.setDomain(SharedPreferencesUtils.loadPreference(generateCookieDomainKey(cookieName)));
		
		String expiryDate = SharedPreferencesUtils.loadPreference(generateCookieExiryDateKey(cookieName));
		if (expiryDate != null) {
			cookie.setExpiryDate(DateUtil.transform(expiryDate));
		}
		
		Log.d(TAG, "Retrieving cookie " + cookieName + "[" + cookie.getValue() + "]");
		return cookie;
	}
	
	/**
	 * @see com.teracode.common.cookie.CookieRepository#getAll()
	 */
	@Override
	public List<Cookie> getAll() {
		List<Cookie> cookies = Lists.newArrayList();
		for (Entry<String, ?> entry : SharedPreferencesUtils.loadAllPreferences().entrySet()) {
			
			// Get all the preferences whose key ends with CookieRepositoryImpl#NAME.
			if (entry.getKey().endsWith(NAME)) {
				cookies.add(get(String.class.cast(entry.getValue())));
			}
		}
		return cookies;
	}
	
	/**
	 * @param cookieName The {@link Cookie}'s name.
	 * @return The key used to save the {@link Cookie}'s name as shared preference.
	 */
	private String generateCookieNameKey(String cookieName) {
		return cookieName + NAME;
	}
	
	/**
	 * @param cookieName The {@link Cookie}'s name.
	 * @return The key used to save the {@link Cookie}'s expiration date as shared preference.
	 */
	private String generateCookieExiryDateKey(String cookieName) {
		return cookieName + EXPIRY_DATE;
	}
	
	/**
	 * @param cookieName The {@link Cookie}'s name.
	 * @return The key used to save the {@link Cookie}'s domain as shared preference.
	 */
	private String generateCookieDomainKey(String cookieName) {
		return cookieName + DOMAIN;
	}
	
	/**
	 * @param cookieName The {@link Cookie}'s name.
	 * @return The key used to save the {@link Cookie}'s value as shared preference.
	 */
	private String generateCookieValueKey(String cookieName) {
		return cookieName + VALUE;
	}
	
	/**
	 * @return The singleton instance.
	 */
	public static CookieRepositoryImpl getInstance() {
		return instance;
	}
}
