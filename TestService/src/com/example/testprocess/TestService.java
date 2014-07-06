package com.example.testprocess;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.util.Log;

public class TestService extends Service {

	private int TEST_START_SERVICE_NOTIFICATION = 10102;
	private int TEST_TIME_NOTIFICATION = 10103;

	private NotificationManager notificationManager;
	private static int count;

	private ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

	@Override
	public void onCreate() {
		super.onCreate();

		this.notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		this.initThread();
	}

	private void doSomething() {
		String message = "Thread Running = " + count++;

		Notification notification = this.getNotification("Test Notification", message, null);
		this.notificationManager.notify(TEST_TIME_NOTIFICATION, notification);
	}

	private void initThread() {
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.nelson_haha);
		BigPictureStyle bigStyle = new NotificationCompat.BigPictureStyle().setBigContentTitle("Haha!").bigPicture(image);

		Notification notification = this.getNotification("Here we go!", "HA! I'm here again!", bigStyle);
		this.notificationManager.notify(TEST_START_SERVICE_NOTIFICATION, notification);

		this.scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				doSomething();
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	private Notification getNotification(final String title, final String message, Style style) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_stat_action_about);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		builder.setContentTitle(title);
		builder.setContentText(message);
		builder.setContentInfo("Info Content");
		builder.setTicker(title);
		builder.setVibrate(new long[] {});
		builder.setLights(Color.YELLOW, 1000, 500);
		if (style != null) {
//			builder.setDefaults(Notification.DEFAULT_SOUND);
//			builder.setSound(Uri.parse("android.resource://com.example.testprocess/" + R.raw.haha));
			builder.setStyle(style);
		}
//		builder.setProgress(100, 1, true);

//		PendingIntent piDismiss = null;
//		builder.addAction(android.R.drawable.ic_lock_silent_mode, "Dismiss", piDismiss);
//		PendingIntent piSnooze = null;
//		builder.addAction(android.R.drawable.ic_dialog_email, "Snooze", piSnooze);

//		Intent resultIntent = new Intent(this, MainActivity.class);
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//		builder.setContentIntent(resultPendingIntent);

		// when delete notification, open the intent
		//builder.setDeleteIntent(resultPendingIntent);

		// when the notification arrive, open the intent
//		builder.setFullScreenIntent(resultPendingIntent, true);

		return builder.build();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		this.restartService();

		super.onDestroy();
	}

	private void restartService() {
		Intent intent = new Intent();
		intent.setAction(TestService.class.getCanonicalName());
		startService(intent);
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		super.onTaskRemoved(rootIntent);
	}

	@Override
	public void onRebind(Intent intent) {
		Log.e(getClass().getSimpleName(), "onRebind");
		super.onRebind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("", "onBind");
		return null;
	}

}