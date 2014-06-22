package com.example.slidingtabs.slidingtabs;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SlidingTabFragmentPagerAdapter extends FragmentPagerAdapter {

	private List<SlidingTabContent> tabs;

	public SlidingTabFragmentPagerAdapter(final FragmentManager fragmentManager, final List<SlidingTabContent> tabs) {
		super(fragmentManager);
		this.tabs = tabs;
	}

	@Override
	public Fragment getItem(int i) {
		return this.tabs.get(i).createFragment();
	}

	@Override
	public int getCount() {
		return this.tabs.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return this.tabs.get(position).getTitle();
	}
}