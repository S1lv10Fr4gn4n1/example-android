package com.example.navigation.tabs;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// since this is an object collection, use a FragmentStatePagerAdapter, and NOT a FragmentPagerAdapter.
public class FruitCollectionPagerAdapter extends FragmentStatePagerAdapter {
	
	private List<Fruit> listFruit;
	
	public FruitCollectionPagerAdapter(final FragmentManager fragmentManager, final List<Fruit> listFruit) {
		super(fragmentManager);
		this.listFruit = listFruit;
	}

	@Override
	public Fragment getItem(int item) {
		return new FruitFragment(this.listFruit.get(item));
	}

	@Override
	public int getCount() {
		return this.listFruit.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return this.listFruit.get(position).getName();
	}

}
