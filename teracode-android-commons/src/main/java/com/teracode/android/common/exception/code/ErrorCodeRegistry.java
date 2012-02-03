package com.teracode.android.common.exception.code;

import java.util.HashMap;
import java.util.Map;

import com.teracode.android.common.application.ApplicationProvider;
import com.teracode.android.common.application.MessageResourcesSpec;
import com.teracode.android.common.util.ClassUtil;
import com.teracode.android.common.util.PropertiesUtil;
import com.teracode.android.common.util.ResourceStringUtils;

/**
 * @author Maxi Rosson
 */
public class ErrorCodeRegistry {

	private static final String PROPERTY_CLASS = "error.code.registry.class";
	private static final String DEFAULT_CLASS = "com.teracode.android.common.exception.code.ErrorCodeRegistry";

	private static ErrorCodeRegistry INSTANCE;

	// Map with all the error codes as keys, and the android resources IDs as
	// values
	private final Map<ErrorCode, Integer> errorCodeMap = new HashMap<ErrorCode, Integer>();

	private ErrorCodeRegistry() {
		MessageResourcesSpec spec = ApplicationProvider.getApp().getMessageResourcesSpec();

		// default mapping.
		errorCodeMap.put(CommonErrorCode.INTERNAL_ERROR, spec.getInternalErrorResourceId());
		errorCodeMap.put(CommonErrorCode.SERVER_ERROR, spec.getServerErrorResourceId());
		errorCodeMap.put(CommonErrorCode.CONNECTION_ERROR, spec.getConnectionErrorResourceId());

		// hook for subclasses.
		this.addErrorCodeMapping(errorCodeMap);
	}

	/**
	 * @return {@link ErrorCodeRegistry}
	 */
	public static ErrorCodeRegistry get() {
		if (INSTANCE == null) {
			String classname = PropertiesUtil.getStringProperty(PROPERTY_CLASS, DEFAULT_CLASS);
			INSTANCE = ClassUtil.newInstance(classname);
		}
		return INSTANCE;
	}

	/**
	 * @param errorCode
	 * @return
	 */
	public Integer getMessageIdFor(ErrorCode errorCode) {
		return errorCodeMap.get(errorCode);
	}

	/**
	 * @param errorCode
	 * @return
	 */
	public String getMessageFor(ErrorCode errorCode) {
		return ResourceStringUtils.getString(this.getMessageIdFor(errorCode));
	}

	/**
	 * @param errorCodeMap
	 */
	protected void addErrorCodeMapping(Map<ErrorCode, Integer> errorCodeMap) {
		// nothing...
	}
}
