package com.example.navigation.tabs;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	private FruitCollectionPagerAdapter collectionPagerAdapter;
	private ViewPager viewPager;
	private ArrayList<Fruit> listFruit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
//		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
//		pagerTabStrip.setDrawFullUnderline(true);
//		pagerTabStrip.setTabIndicatorColor(Color.RED);
		
		this.viewPager = (ViewPager) findViewById(R.id.pager);
		
		this.loadData();
		
		this.collectionPagerAdapter = new FruitCollectionPagerAdapter(this.getSupportFragmentManager(), this.listFruit);
		this.viewPager.setAdapter(this.collectionPagerAdapter);

//		this.viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//			@Override
//			public void onPageSelected(int position) {
//				// when swiping between pages, select the corresponding tab.
//				getActionBar().setSelectedNavigationItem(position);
//			}
//		});

//		final ActionBar actionBar = getActionBar();
		// specify that tabs should be displayed in the action bar.
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create a tab listener that is called when the user changes tabs.
//		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//				// When the tab is selected, switch to the corresponding page in the ViewPager.
//				viewPager.setCurrentItem(tab.getPosition());
//			}
//
//			public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//				// hide the given tab
//			}
//
//			public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//				// probably ignore this event
//			}
//		};

//		// Add 3 tabs, specifying the tab's text and TabListener
//		for (int i = 0; i < 3; i++) {
//			actionBar.addTab(actionBar.newTab().setText("Tab " + (i + 1)).setTabListener(tabListener));
//		}
	}
	
	private void loadData() {
		this.listFruit = new ArrayList<Fruit>();
		this.listFruit.add(new Fruit(getString(R.string.label_avocado), R.drawable.avocado));
		this.listFruit.add(new Fruit(getString(R.string.label_orange), R.drawable.orange));
		this.listFruit.add(new Fruit(getString(R.string.label_banada), R.drawable.banana));
		this.listFruit.add(new Fruit(getString(R.string.label_strawberry), R.drawable.strawberry));
		this.listFruit.add(new Fruit(getString(R.string.label_apple), R.drawable.apple));
		this.listFruit.add(new Fruit(getString(R.string.label_lemon), R.drawable.lemon));
		this.listFruit.add(new Fruit(getString(R.string.label_watermelon), R.drawable.watermelon));
		this.listFruit.add(new Fruit(getString(R.string.label_grapes), R.drawable.grapes));
		this.listFruit.add(new Fruit(getString(R.string.label_pear), R.drawable.pear));
		this.listFruit.add(new Fruit(getString(R.string.label_cherry), R.drawable.cherry));
		this.listFruit.add(new Fruit(getString(R.string.label_peach), R.drawable.peach));
		this.listFruit.add(new Fruit(getString(R.string.label_pineapple), R.drawable.pineapple));
		this.listFruit.add(new Fruit(getString(R.string.label_papaya), R.drawable.papaya));
	}

}
