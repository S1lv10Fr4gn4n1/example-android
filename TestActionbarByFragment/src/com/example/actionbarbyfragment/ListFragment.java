package com.example.actionbarbyfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
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
import android.widget.TextView;
import android.widget.Toast;

public class ListFragment extends Fragment {

	private ListView listview;
	private ListFragmentAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.setHasOptionsMenu(true);

		View view = inflater.inflate(R.layout.list, container, false);

		final List<String> list = new ArrayList<String>();
		list.add("Avocado");
		list.add("Orange");
		list.add("Banada");
		list.add("Strawberry");
		list.add("Apple");
		list.add("Lemon");
		list.add("Watermelon");
		list.add("Grapes");
		list.add("Pear");
		list.add("Cherry");
		list.add("Peach");
		list.add("Pineapple");
		list.add("Papaya");
		this.adapter = new ListFragmentAdapter(getActivity(), list);

		this.listview = (ListView) view.findViewById(R.id.list_listview);
		registerForContextMenu(this.listview);
		this.listview.setAdapter(this.adapter);
		this.listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FruittFragment fragment = new FruittFragment();
				Bundle args = new Bundle();
				args.putString("text", list.get(position));
				fragment.setArguments(args);
				switchFragment(fragment);
			}
		});

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.list_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_add:
				Toast.makeText(getActivity(), "Add!", Toast.LENGTH_SHORT).show();
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
//		    YourObject obj = (YourObject) lv.getItemAtPosition(acmi.position);
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
			case R.id.action_add:
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
