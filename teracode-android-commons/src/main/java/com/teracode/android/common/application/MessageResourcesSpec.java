package com.teracode.android.common.application;

/**
 * @author Luciano Rey
 */
public interface MessageResourcesSpec {

	/**
	 * @return id of resource for loading message.
	 */
	public int getLoadingMessageResourceId();

	/**
	 * @return id of text resource for notification error title
	 */
	public int getNotificationErrorTitleResourceId();

	/**
	 * @return id of text resource for notification error body.
	 */
	public int getNotificationErrorTextResourceId();

	/**
	 * @return
	 */
	public int getInternalErrorResourceId();

	/**
	 * @return
	 */
	public int getServerErrorResourceId();

	/**
	 * @return
	 */
	public int getConnectionErrorResourceId();

	public int getReportDialogTitleResourceId();

	public int getReportDialogTextResourceId();

	public int getOkResourceId();

}
