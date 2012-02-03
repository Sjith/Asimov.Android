package com.teracode.android.common.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.teracode.android.common.R;
import com.teracode.android.common.application.ApplicationProvider;

/**
 * @author Maxi Rosson
 * @author Fernando Perez
 */
public final class NotificationUtil {

	private final static NotificationManager NOTIFICATION_MANAGER = (NotificationManager) ApplicationProvider.get().getActiveApplication().getSystemService(Context.NOTIFICATION_SERVICE);

	/**
	 * Avoid instantiation
	 */
	private NotificationUtil() {
		// Do nothing...
	}

	public static void sendNotification(int id, int icon, int tickerText, int contentTitle, int contentText) {
		sendNotification(id, icon, tickerText, contentTitle, contentText, null);
	}

	public static void sendNotification(int id, int icon, int tickerText, int contentTitle, int contentText, Class<?> clazz) {
		sendNotification(id, icon, tickerText, contentTitle, contentText, clazz, System.currentTimeMillis(), null);
	}

	public static void sendNotification(int id, int icon, int tickerText, int contentTitle, int contentText, Class<?> clazz, long when) {
		sendNotification(id, icon, tickerText, contentTitle, contentText, clazz, when, null);
	}

	public static void sendNotification(int id, int icon, int tickerText, int contentTitle, int contentText, Class<?> clazz, long when, Bundle notificationBundle) {
		Context context = ApplicationProvider.get().getActiveApplication();
		sendNotification(id, icon, context.getString(tickerText), context.getString(contentTitle), context.getString(contentText), clazz, when, notificationBundle);
	}

	public static void sendNotification(int id, int icon, String tickerText, String contentTitle, String contentText, Class<?> clazz, long when, Bundle notificationBundle) {

		Notification notification = createNotification(icon, tickerText, clazz, when, notificationBundle);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Context context = ApplicationProvider.get().getActiveApplication();
		notification.setLatestEventInfo(context, contentTitle, contentText, notification.contentIntent);

		sendNotification(id, notification);
	}

	public static void sendNotification(int id, int statusBarIcon, int notificationIcon, int tickerText, int contentTitle, int contentText, Class<?> clazz) {
		sendNotification(id, statusBarIcon, notificationIcon, tickerText, contentTitle, contentText, clazz, System.currentTimeMillis(), null);
	}

	public static void sendNotification(int id, int statusBarIcon, int notificationIcon, int tickerText, int contentTitle, int contentText, Class<?> clazz, long when) {
		sendNotification(id, statusBarIcon, notificationIcon, tickerText, contentTitle, contentText, clazz, when, null);
	}

	/**
	 * @param id
	 * @param statusBarIcon
	 * @param notificationIcon
	 * @param tickerText
	 * @param contentTitle
	 * @param contentText
	 * @param clazz
	 * @param when
	 * @param notificationBundle
	 */
	public static void sendNotification(int id, int statusBarIcon, int notificationIcon, int tickerText, int contentTitle, int contentText, Class<?> clazz, long when, Bundle notificationBundle) {

		Notification notification = createNotification(statusBarIcon, tickerText, clazz, when, notificationBundle);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		notification.contentView = new RemoteViews(ApplicationEnvironmentUtil.getPackageName(), R.layout.simple_notification);
		notification.contentView.setImageViewResource(R.id.simpleNotificationIconImage, notificationIcon);
		notification.contentView.setTextViewText(R.id.simpleNotificationIconTitle, LocalizedStringUtil.getString(contentTitle));

		sendNotification(id, notification);
	}

	public static void sendInProgressNotification(int id, int statusBarIcon, int notificationIcon, int progress, int tickerText, int contentTitle, int actionText, Class<?> clazz) {
		sendInProgressNotification(id, statusBarIcon, notificationIcon, progress, tickerText, contentTitle, actionText, clazz, null);
	}

	public static void sendInProgressNotification(int id, int statusBarIcon, int notificationIcon, int progress, int tickerText, int contentTitle, int actionText, Class<?> clazz,
			Bundle notificationBundle) {

		Notification notification = createNotification(statusBarIcon, tickerText, clazz, 0, notificationBundle);
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		Context context = ApplicationProvider.get().getActiveApplication();
		notification.contentView = new RemoteViews(context.getPackageName(), R.layout.simple_notification_inprogress);
		notification.contentView.setTextViewText(R.id.simpleNotificationIconTitle, context.getString(contentTitle));
		notification.contentView.setTextViewText(R.id.simpleNotificationInProgressAction, context.getString(actionText));
		notification.contentView.setTextViewText(R.id.progressPercentage, progress + "%");
		notification.contentView.setProgressBar(R.id.progressBar, 100, progress, false);
		notification.contentView.setImageViewResource(R.id.simpleNotificationIconImage, notificationIcon);

		sendNotification(id, notification);
	}

	private static Notification createNotification(int statusBarIcon, int tickerText, Class<?> clazz, long when, Bundle notificationBundle) {
		return createNotification(statusBarIcon, ApplicationProvider.get().getActiveApplication().getString(tickerText), clazz, when, notificationBundle);
	}

	private static Notification createNotification(int statusBarIcon, String tickerText, Class<?> clazz, long when, Bundle notificationBundle) {
		Context context = ApplicationProvider.get().getActiveApplication().getCurrentActivity();
		Notification notification = new Notification(statusBarIcon, tickerText, when);

		Intent notificationIntent = clazz != null ? new Intent(context, clazz) : new Intent();
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (notificationBundle != null) {
			notificationIntent.replaceExtras(notificationBundle);
		}
		notification.contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		return notification;
	}

	public static void sendNotification(int id, Notification notification) {
		NOTIFICATION_MANAGER.notify(id, notification);
	}

	public static void cancelNotification(int id) {
		NOTIFICATION_MANAGER.cancel(id);
	}
}