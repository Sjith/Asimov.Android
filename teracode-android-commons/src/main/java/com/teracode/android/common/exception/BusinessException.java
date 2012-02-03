package com.teracode.android.common.exception;

import com.teracode.android.common.exception.code.ErrorCode;

/**
 * Expected Business exception
 * 
 * @author Luciano Rey
 */
@SuppressWarnings("serial")
public class BusinessException extends AndroidException {

	private ErrorCode errorCode;

	/**
	 * @param errorCode {@link ErrorCode}
	 */
	public BusinessException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
