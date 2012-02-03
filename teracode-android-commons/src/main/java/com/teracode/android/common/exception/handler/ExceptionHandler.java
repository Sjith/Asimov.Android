package com.teracode.android.common.exception.handler;

import com.teracode.android.common.exception.AndroidException;

/**
 * @author Fernando Perez
 * 
 * Common Interface for all the Exception Handlers
 */
public interface ExceptionHandler<T extends AndroidException> {

	/**
	 * Handle the exception and execute the alternative flow based on the Exception's type
	 */
	public void handle(T exception);
}
