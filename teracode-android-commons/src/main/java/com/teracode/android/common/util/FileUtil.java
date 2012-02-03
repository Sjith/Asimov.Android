package com.teracode.android.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.util.Log;

import com.teracode.android.common.exception.code.CommonErrorCode;

/**
 * This class contains functions for working with files within the application.
 * 
 * @author Fernando Perez
 */
public final class FileUtil {

	private static final String TAG = FileUtil.class.getSimpleName();
	private static final int BUFFER_SIZE = 1024;

	/**
	 * Private constructor to avoid instance creations
	 */
	private FileUtil() {
		// Do nothing...
	}

	/**
	 * Deletes an instance of {@link File} even if it is a directory containing files.<br>
	 * If the file is a directory and has contents, then executes itself on every content.
	 * 
	 * @see File#delete()
	 * @param file The {@link File} to be deleted.
	 */
	public static void forceDelete(File file) {
		if (file.exists()) {

			// If the File instance to delete is a directory, delete all it's contents.
			if (file.isDirectory()) {
				for (File contentFile : file.listFiles()) {
					FileUtil.forceDelete(contentFile);
				}
			}

			if (file.delete()) {
				Log.i(TAG, "File " + file.getPath() + " was succesfully deleted.");
			} else {
				Log.w(TAG, "File " + file.getPath() + " couldn't be deleted.");
			}
		}
	}

	/**
	 * 
	 * @param closeable
	 */
	public static void safeClose(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			Log.w(TAG, "Exception thrown when trying to close the closeable", e);
		}
	}

	/**
	 * 
	 * @param is
	 * @param os
	 */
	public static void copyStream(InputStream is, OutputStream os) {
		int count = 0;
		byte[] bytes = new byte[BUFFER_SIZE];
		try {
			while ((count = is.read(bytes, 0, BUFFER_SIZE)) != -1) {
				os.write(bytes, 0, count);
			}
		} catch (IOException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
		}
	}
}