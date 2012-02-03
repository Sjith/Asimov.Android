package com.teracode.android.common.exception.code;

import com.teracode.android.common.exception.ApplicationException;
import com.teracode.android.common.exception.BusinessException;
import com.teracode.android.common.exception.ProgramException;

/**
 * @author Maxi Rosson
 */
public enum CommonErrorCode implements ErrorCode {
	
	SERVER_ERROR,
	INTERNAL_ERROR,
	CONNECTION_ERROR;
	
	/**
	 * @see com.teracode.android.common.exception.code.ErrorCode#newBusinessException()
	 */
	@Override
	public BusinessException newBusinessException() {
		return new BusinessException(this);
	}

	/**
	 * @see com.teracode.android.common.exception.code.ErrorCode#newApplicationException(java.lang.Throwable)
	 */
	@Override
	public ApplicationException newApplicationException(Throwable throwable) {
		return new ApplicationException(this, throwable);
	}

	/**
	 * @see com.teracode.android.common.exception.code.ErrorCode#newApplicationException(java.lang.String)
	 */
	@Override
	public ApplicationException newApplicationException(String message) {
		return new ApplicationException(this, message);
	}

	/**
	 * @see com.teracode.android.common.exception.code.ErrorCode#newProgramException(java.lang.Throwable)
	 */
	@Override
	public ProgramException newProgramException(Throwable throwable) {
		return new ProgramException(this, throwable);
	}

	/**
	 * @see com.teracode.android.common.exception.code.ErrorCode#newProgramException(java.lang.String)
	 */
	@Override
	public ProgramException newProgramException(String message) {
		return new ProgramException(this, message);
	}
}
