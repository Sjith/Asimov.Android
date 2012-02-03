package com.teracode.android.framework.application;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import com.google.inject.Module;
import com.teracode.android.common.application.AbstractApplication;
import com.teracode.android.common.exception.handler.DefaultUncaughtExceptionHandler;
import com.teracode.android.common.util.ApplicationInfoUtil;
import com.teracode.android.common.util.FileUtil;
import com.teracode.android.framework.module.AsimovModule;

/**
 * @author Fernando Perez
 */
public abstract class AndroidApplication extends AbstractApplication {

	private File cacheDirectory;

	/**
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		this.setUncaughtExceptionHandler(this.getUncaughtExceptionHandler());
		this.initCacheDirectory();
	}

	/**
	 * @see com.teracode.android.common.application.AbstractApplication#getCacheDirectory()
	 */
	@Override
	public File getCacheDirectory() {
		return this.cacheDirectory;
	}
	
	/**
	 * @see roboguice.application.GuiceApplication#addApplicationModules(java.util.List)
	 */
	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new AsimovModule());
	}
	
	/**
	 * @return {@link UncaughtExceptionHandler}
	 */
	protected UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return DefaultUncaughtExceptionHandler.get();		
	}
	
	/**
	 * Configures the cache dir for the whole application 
	 * If some media storage is mounted it will try to use it, if not it will ask to the OS for a cache directory.
	 */
	private void initCacheDirectory() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			cacheDirectory = new File(android.os.Environment.getExternalStorageDirectory(), ApplicationInfoUtil.getPackageName());
			// Remove the content of the cache dir
			FileUtil.forceDelete(cacheDirectory);
		} else {
			cacheDirectory = getCacheDir();
		}
		
		if (!cacheDirectory.exists()) {
			cacheDirectory.mkdirs();
		}
	}
	
	/**
	 * @param handler {@link UncaughtExceptionHandler}
	 */
	private void setUncaughtExceptionHandler(UncaughtExceptionHandler handler) {
		Thread.setDefaultUncaughtExceptionHandler(handler);
	}
}
