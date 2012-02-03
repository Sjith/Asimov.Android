package com.teracode.android.common.exception.code;

import com.teracode.android.common.exception.ApplicationException;
import com.teracode.android.common.exception.BusinessException;
import com.teracode.android.common.exception.ProgramException;

/**
 * Common interface for all the possible errors of the application
 * 
 * @author Maxi Rosson
 */
public interface ErrorCode {
	
	/**
	 * @return A new {@link BusinessException} with this {@link ErrorCode}
	 */
	public BusinessException newBusinessException();
	
	/**
	 * @param throwable {@link Throwable}
	 * @return A new {@link ApplicationException} with this {@link ErrorCode}
	 */
	public ApplicationException newApplicationException(Throwable throwable);
	
	/**
	 * @param message
	 * @return A new {@link ApplicationException} with this message
	 */
	public ApplicationException newApplicationException(String message);

	/**
	 * @param throwable {@link Throwable}
	 * @return A new {@link ProgramException} with this {@link ErrorCode}
	 */
	public ProgramException newProgramException(Throwable throwable);
	
	/**
	 * @param message
	 * @return A new {@link ProgramException} with this message
	 */
	public ProgramException newProgramException(String message);
	
}
