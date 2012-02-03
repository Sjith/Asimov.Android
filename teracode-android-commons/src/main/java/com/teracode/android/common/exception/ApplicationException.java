package com.teracode.android.common.exception;

import com.teracode.android.common.exception.code.ErrorCode;

/**
 * Expected Application exception not related with business logic. <br>
 * For example a time out or I/O error.
 * 
 * @author Luciano Rey
 */
@SuppressWarnings("serial")
public class ApplicationException extends AndroidException {

	private final ErrorCode errorCode;

	/**
	 * @param errorCode {@link ErrorCode}
	 * @param throwable {@link Throwable}
	 */
	public ApplicationException(ErrorCode errorCode, Throwable throwable) {
		super(throwable);

		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode {@link ErrorCode}
	 * @param message
	 */
	public ApplicationException(ErrorCode errorCode, String message) {
		super(message);

		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}