package com.example.testprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent();
		intent.setAction(TestService.class.getCanonicalName());
		startService(intent);
		
//		AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//		PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
//		
//		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//			alarmMgr.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, alarmIntent);
//		} else {
//			alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//										 AlarmManager.INTERVAL_HALF_HOUR,
//										 AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
//			
//		}
		
	}
}
