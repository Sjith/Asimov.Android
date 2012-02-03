package com.teracode.android.common.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.util.Log;

/**
 * @author Luciano Rey
 */
public final class ThreadUtil {
	
	private static final String TAG = ThreadUtil.class.getSimpleName();
	
	// Default amount of thread inside the pool
	private static final int DEFAULT_THREAD_POOL_SIZE = 10;
	
	private static final String THREAD_POOL_SIZE_PROPERTY = "thread.pool.size";
	
	private static final Executor executor = Executors.newFixedThreadPool(PropertiesUtil.getIntegerProperty(
		THREAD_POOL_SIZE_PROPERTY, DEFAULT_THREAD_POOL_SIZE));

	/**
	 * Private constructor to avoid instance creations
	 */
	public ThreadUtil() {
		// Do nothing...
	}

	/**
	 * @param runnable
	 */
	public static void execute(Runnable runnable) {
		ThreadUtil.executor.execute(runnable);
	}
	
	/**
	 * @param seconds
	 */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			Log.e(TAG, "Error sleep in millis", e);
		}
	}
	
	/**
	 * @param millis
	 */
	public static void sleepInMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Log.e(TAG, "Error sleep in millis", e);
		}
	}
	
}
