package com.example.slidingtabs;

import android.os.Bundle;

import com.example.slidingtabs.slidingtabs.AbstractSlidingTabFragment;
import com.example.slidingtabs.slidingtabs.SlidingTabContent;

public class ResultSearch extends AbstractSlidingTabFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = new Bundle();
		bundle.putString("text", "Stream");
		tabs.add(new SlidingTabContent(FragmentTest.class, bundle, "Stream"));
		
		tabs.add(new SlidingTabContent(FragmentTest.class, null, "Messages"));
		tabs.add(new SlidingTabContent(FragmentTest.class, null, "Photos"));
		tabs.add(new SlidingTabContent(FragmentTest.class, null, "Notifications"));
	}

}
