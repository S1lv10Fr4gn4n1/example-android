package com.example.actionbarbyfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class ListFragment extends Fragment {

	private ListView listview;
	private ListFragmentAdapter adapter;

	private ActionMode.Callback actionModeCallback;
	private ArrayList<String> listFruit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.setHasOptionsMenu(true);

		View view = inflater.inflate(R.layout.list, container, false);
		this.initComponents(view);
		this.setListeners();
		this.loadData();

		return view;
	}

	private void initComponents(View view) {
		this.listview = (ListView) view.findViewById(R.id.list_listview);
		this.registerForContextMenu(this.listview);
		
		this.actionModeCallback = new ActionMode.Callback() {
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.list_menu, menu);
				return true;
			}

			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
					case R.id.list_menu_action_add:
						Toast.makeText(getActivity(), "Action mode (+)", Toast.LENGTH_SHORT).show();
						mode.finish();
						return true;
					default:
						return false;
				}
			}

			public void onDestroyActionMode(ActionMode mode) {
				Toast.makeText(getActivity(), "OK Selected", Toast.LENGTH_SHORT).show();
			}
		};
	}

	private void setListeners() {
		this.listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FruittFragment fragment = new FruittFragment();
				Bundle args = new Bundle();
				args.putString("text", listFruit.get(position));
				fragment.setArguments(args);
				switchFragment(fragment);
			}
		});
	}

	private void loadData() {
		this.listFruit = new ArrayList<String>();
		this.listFruit.add("Avocado");
		this.listFruit.add("Orange");
		this.listFruit.add("Banada");
		this.listFruit.add("Strawberry");
		this.listFruit.add("Apple");
		this.listFruit.add("Lemon");
		this.listFruit.add("Watermelon");
		this.listFruit.add("Grapes");
		this.listFruit.add("Pear");
		this.listFruit.add("Cherry");
		this.listFruit.add("Peach");
		this.listFruit.add("Pineapple");
		this.listFruit.add("Papaya");

		this.initAdapter();
	}

	private void initAdapter() {
		this.adapter = new ListFragmentAdapter(getActivity(), this.listFruit);
		this.listview.setAdapter(this.adapter);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.list_menu, menu);

		// SEARCH
		MenuItem searchItem = menu.findItem(R.id.list_menu_action_search);
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

		// SHARE
		MenuItem shareItem = menu.findItem(R.id.list_menu_action_share);
		ShareActionProvider shareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
		shareActionProvider.setShareIntent(getDefaultIntent());
	}

	private Intent getDefaultIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TITLE, "Title Hello");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello World");
		return intent;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.list_menu_action_add:
				Toast.makeText(getActivity(), "Add!", Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.list_menu_action_mode:
				getActivity().startActionMode(this.actionModeCallback);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == R.id.list_listview) {
			ListView lv = (ListView) v;
			AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
			String string = (String) lv.getItemAtPosition(acmi.position);

			menu.add("One");
			menu.add("Two");
			menu.add("Three");
			menu.add(string);
		}

//		menu.setHeaderTitle("Context Menu");
//		getActivity().getMenuInflater().inflate(R.menu.list_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.list_menu_action_add:
				Toast.makeText(getActivity(), "Add!", Toast.LENGTH_SHORT).show();
				return true;
		}
//		if (item.getTitle() == "Action 1") {
//			Toast.makeText(getActivity(), "Action 1!", Toast.LENGTH_SHORT).show();
//		} else if (item.getTitle() == "Action 2") {
//			Toast.makeText(getActivity(), "Action 2!", Toast.LENGTH_SHORT).show();
//		} else {
//			return false;
//		}
		return true;
	}

	private void switchFragment(final Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private class ListFragmentAdapter extends BaseAdapter {

		private List<String> list;
		private Context context;

		public ListFragmentAdapter(final Context context, final List<String> list) {
			super();
			this.list = list;
			this.context = context;
		}

		@Override
		public int getCount() {
			return this.list.size();
		}

		@Override
		public Object getItem(int position) {
			return this.list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return this.list.get(position).hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);
			}

			TextView textView = ViewHolder.get(convertView, R.id.list_item_text);
			textView.setText(this.list.get(position));

			return convertView;
		}
	}

	public static class ViewHolder {
		@SuppressWarnings("unchecked")
		public static <T extends View> T get(View view, int id) {
			SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
			if (viewHolder == null) {
				viewHolder = new SparseArray<View>();
				view.setTag(viewHolder);
			}
			View childView = viewHolder.get(id);
			if (childView == null) {
				childView = view.findViewById(id);
				viewHolder.put(id, childView);
			}
			return (T) childView;
		}
	}
}
