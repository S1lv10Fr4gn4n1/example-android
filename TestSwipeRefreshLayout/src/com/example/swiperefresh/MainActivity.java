package com.example.swiperefresh;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
			super();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_main, container, false);

			final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
			swipeLayout.setOnRefreshListener(new OnRefreshListener() {
				@Override
				public void onRefresh() {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							swipeLayout.setRefreshing(false);
						}
					}, 5000);
				}
			});
			swipeLayout.setColorScheme(android.R.color.holo_green_light, 
									   android.R.color.holo_green_dark, 
									   android.R.color.holo_blue_light, 
									   android.R.color.holo_blue_dark);
//			swipeLayout.setEnabled(false);

			return view;
		}
	}

}
