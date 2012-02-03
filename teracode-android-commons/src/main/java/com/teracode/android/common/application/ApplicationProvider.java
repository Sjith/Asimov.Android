package com.teracode.android.common.application;


/**
 * This class is in charge of handle the application instance.
 * 
 * @author Fernando Perez
 */
public final class ApplicationProvider {

	private static ApplicationProvider applicationProvider = new ApplicationProvider();
	private AbstractApplication application;

	/**
	 * Constructor
	 */
	private ApplicationProvider() {
		// Do nothing...
	}

	/**
	 * @return {@link ApplicationProvider}
	 */
	public static ApplicationProvider get() {
		return applicationProvider;
	}

	/**
	 * @return {@link AbstractApplication}
	 */
	public static AbstractApplication getApp() {
		return get().getActiveApplication();
	}
	
	/**
	 * @param application {@link AbstractApplication}
	 */
	public void loadActiveApplication(AbstractApplication application) {
		this.application = application;
	}

	/**
	 * @return {@link AbstractApplication}
	 */
	public AbstractApplication getActiveApplication() {
		return this.application;
	}
}
