package com.example.drawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private String[] languageTitles;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.languageTitles = getResources().getStringArray(R.array.languages);
		this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		this.listview = (ListView) findViewById(R.id.left_drawer);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);

		// set a custom shadow that overlays the main content when the drawer opens
		this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		this.drawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.drawable.ic_drawer, R.string.label_drawer_open, R.string.label_drawer_close);

//		this.drawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.drawable.ic_drawer, R.string.label_drawer_open, R.string.label_drawer_close) {
//			// called when a drawer has settled in a completely closed state.
//			public void onDrawerClosed(View view) {
//				invalidateOptionsMenu();
//			}
//			// called when a drawer has settled in a completely open state.
//			public void onDrawerOpened(View drawerView) {
//				invalidateOptionsMenu();
//			}
//		};

//		drawerLayout.setDrawerListener(new DrawerListener() {
//			public void onDrawerStateChanged(int arg0) {
//			}
//			public void onDrawerSlide(View arg0, float arg1) {
//			}
//			public void onDrawerOpened(View arg0) {
//			}
//			public void onDrawerClosed(View arg0) {
//			}
//		});

		// set the adapter for the list view
		this.listview.setAdapter(new LanguageAdapter(this, languageTitles));

		// set the list's click listener
		this.listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				view.setSelected(true);
				selectLanguage(position);
			}
		});

		this.drawerLayout.setDrawerListener(drawerToggle);

		if (savedInstanceState == null) {
			this.selectLanguage(0);
		}
	}
	
	@Override
	public void onBackPressed() {
		
		
		super.onBackPressed();
	}

	private void selectLanguage(int index) {
		String language = languageTitles[index];

		LanguageFragment fragment = new LanguageFragment();

		Bundle bundle = new Bundle();
		bundle.putString(LanguageFragment.TITLE, language);
		fragment.setArguments(bundle);

		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.content_frame, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		this.listview.setItemChecked(index, true);
		this.drawerLayout.closeDrawer(this.listview);
		this.setTitle(language);
		this.getSupportActionBar().setTitle(language);
		this.getSupportActionBar().setSubtitle("Language");
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		this.drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		this.drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	// called whenever we call invalidateOptionsMenu()
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = drawerLayout.isDrawerOpen(listview);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
}
