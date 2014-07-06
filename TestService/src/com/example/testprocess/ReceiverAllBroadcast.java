package com.example.testprocess;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverAllBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!this.isMyServiceRunning(context)) {
			Intent i = new Intent();
			i.setAction(TestService.class.getCanonicalName());
			context.startService(i);
		} else {
			Log.e("", "Service is running");
		}
	}

	private boolean isMyServiceRunning(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (TestService.class.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
