package com.example.slidingpanel;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuFragment extends Fragment {

	private ListView listView;
	private MenuItemListener menuItemListener;
	private List<Fruit> list;

	public MenuFragment(List<Fruit> list) {
		super();
		this.list = list;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getActivity() instanceof MenuItemListener) {
			this.menuItemListener = (MenuItemListener) getActivity();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		this.initComponents(view);
		this.setListeners();
		this.loadData();
		return view;
	}

	private void initComponents(View view) {
		this.listView = (ListView) view.findViewById(R.id.menu_fragment_listview);
	}

	private void loadData() {
		MenuAdapter adapter = new MenuAdapter(getActivity(), this.list);
		this.listView.setAdapter(adapter);
	}

	private void setListeners() {
		this.listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				view.setSelected(true);
				menuItemListener.itemSelected(position);
			}
		});
	}
}
