package com.teracode.android.common.exception.handler;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.common.exception.ApplicationException;
import com.teracode.android.common.exception.BusinessException;
import com.teracode.android.common.exception.ProgramException;

/**
 * Handle cases where a thread is being terminated by an uncaught exception. 
 * Upon such termination, the handler is notified of the terminating thread and causal exception.
 * Depending on the exception type it will delegate the handle to the correspondient Exception Handler 
 * If there is no explicit handler set then the thread's group is the default handler. 
 * 
 * @author Fernando Perez
 */
public class DefaultUncaughtExceptionHandler extends Handler implements UncaughtExceptionHandler {

	private static final DefaultUncaughtExceptionHandler instance = new DefaultUncaughtExceptionHandler();

	private UncaughtExceptionHandler defaultExceptionHandler;
	private Map<Class<?>, ExceptionHandler<?>> exceptionHandlers = new HashMap<Class<?>, ExceptionHandler<?>>();

	private DefaultUncaughtExceptionHandler() {
		this.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		this.initExceptionHandlers();
	}

	/**
	 * @return {@link DefaultUncaughtExceptionHandler}
	 */
	public static DefaultUncaughtExceptionHandler get() {
		return instance;
	}

	/**
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@SuppressWarnings("unchecked")
	public void uncaughtException(Thread thread, Throwable throwable) {
		if(throwable instanceof AndroidException) {
			if (!exceptionHandlers.containsKey(throwable.getClass())) {
				this.defaultExceptionHandler.uncaughtException(thread, throwable);
			} else {
				@SuppressWarnings("rawtypes")
				ExceptionHandler handler = exceptionHandlers.get(throwable.getClass());
				handler.handle((AndroidException) throwable);
			}
		}
		else {
			this.defaultExceptionHandler.uncaughtException(thread, throwable);
		}
	}

	protected void initExceptionHandlers() {
		exceptionHandlers.put(BusinessException.class, new BusinessExceptionHandler());
		exceptionHandlers.put(ProgramException.class, new ProgramExceptionHandler());
		exceptionHandlers.put(ApplicationException.class, new ApplicationExceptionHandler());
	}
}