package com.example.slidingpanel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity implements MenuItemListener {

	private SlidingPaneLayout slidingPaneLayout;
	private List<Fruit> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.initComponents();
		this.loadData();
		this.initFragments();
	}

	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 2) {
			super.onBackPressed();
		} else {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.menu, menu);

		MenuItem searchItem = menu.findItem(R.id.menu_action_search);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		searchView.setQueryHint(getString(R.string.hint_search));
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				Log.d(getClass().getSimpleName(), "onQueryTextSubmit");
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.d(getClass().getSimpleName(), "onQueryTextChange");
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private void initComponents() {
		this.slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_panel);
		this.slidingPaneLayout.setParallaxDistance(100);
		this.slidingPaneLayout.setShadowResource(R.drawable.sliding_pane_shadow);

		this.getActionBar().setDisplayHomeAsUpEnabled(true);
		this.getActionBar().setDisplayUseLogoEnabled(true);

		this.getActionBar().setDisplayShowTitleEnabled(true);
		this.getActionBar().setTitle("Fruits");
	}

	private void initFragments() {
		this.switchContent(new MenuFragment(this.list), R.id.menu);
		this.switchContent(new DetailFragment(new Fruit("Fruits", R.drawable.fruits)));
	}

	private void loadData() {
		this.list = new ArrayList<Fruit>();
		this.list.add(new Fruit("Pineapple", R.drawable.pineapple));
		this.list.add(new Fruit("Banana", R.drawable.banada));
		this.list.add(new Fruit("Avocado", R.drawable.avocado));
		this.list.add(new Fruit("Apple", R.drawable.apple));
		this.list.add(new Fruit("Watermelon", R.drawable.watermelon));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void switchContent(final Fragment fragment) {
		this.switchContent(fragment, R.id.detail);
	}

	private void switchContent(final Fragment fragment, int idRes) {
		FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
		transaction.replace(idRes, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void toggle() {
		if (this.slidingPaneLayout.isOpen()) {
			this.slidingPaneLayout.closePane();
		} else {
			this.slidingPaneLayout.openPane();
		}
	}

	@Override
	public void itemSelected(int position) {
		this.toggle();

		Fruit fruit = this.list.get(position);
		this.switchContent(new DetailFragment(fruit));
//		this.getActionBar().setSubtitle(fruit.getName());
	}

}
