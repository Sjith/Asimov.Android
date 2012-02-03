package com.teracode.android.common.util;

import java.util.Random;

/**
 * @author Fernando Perez
 * @author Maxi Rosson
 */
public final class IdGeneratorUtil {

	private static int ID = 10000;

	/**
	 * Avoid instantiation
	 */
	private IdGeneratorUtil() {
		// Do nothing...
	}

	/**
	 * @return
	 */
	public static long getLongId() {
		return ID++;
	}

	/**
	 * @return
	 */
	public static int getIntId() {
		return ID++;
	}

	/**
	 * @return
	 */
	public static long getRandomLongId() {
		return new Random().nextLong();
	}

	/**
	 * @return
	 */
	public static int getRandomIntId() {
		return new Random().nextInt();
	}
}