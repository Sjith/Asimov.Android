package com.teracode.android.common.exception;

import com.teracode.android.common.exception.code.ErrorCode;

/**
 * @author Fernando Perez
 */
@SuppressWarnings("serial")
public class ProgramException extends AndroidException {

	private ErrorCode errorCode;

	/**
	 * 
	 */
	public ProgramException() {
		super();
	}

	/**
	 * @param errorCode {@link ErrorCode}
	 */
	public ProgramException(ErrorCode errorCode, Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode {@link ErrorCode}
	 */
	public ProgramException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param message
	 */
	public ProgramException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProgramException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ProgramException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}