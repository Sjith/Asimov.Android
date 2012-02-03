package com.teracode.android.common.util;

import com.teracode.android.common.exception.ProgramException;

/**
 * @author Luciano Rey
 */
public class ClassUtil {

	/**
	 * @param <T>
	 * @param classname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T newInstance(String classname) {
		try {
			Class<T> clazz = (Class<T>) ClassUtil.class.getClassLoader().loadClass(classname);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			throw new ProgramException(e);
		}
	}

	/**
	 * @param <T>
	 * @param type
	 * @return
	 */
	public static <T extends Object> T newInstance(Class<T> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			throw new ProgramException(e);
		} catch (IllegalAccessException e) {
			throw new ProgramException(e);
		}
	}
}
