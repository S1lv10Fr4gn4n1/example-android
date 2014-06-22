package com.example.slidingtabs.slidingtabs;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slidingtabs.R;

public class AbstractSlidingTabFragment extends Fragment {
	private SlidingTabLayout slidingTabLayout;
	private ViewPager viewPager;

	protected List<SlidingTabContent> tabs = new ArrayList<SlidingTabContent>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sliding_fragment, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		this.viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		this.viewPager.setAdapter(new SlidingTabFragmentPagerAdapter(getChildFragmentManager(), tabs));

		this.slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
		this.slidingTabLayout.setViewPager(this.viewPager);

		this.slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			public int getIndicatorColor(int position) {
				return tabs.get(position).getIndicatorColor();
			}

			public int getDividerColor(int position) {
				return tabs.get(position).getDividerColor();
			}
		});
	}

}
