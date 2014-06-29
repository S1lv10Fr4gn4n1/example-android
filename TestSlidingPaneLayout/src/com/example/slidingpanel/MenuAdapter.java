package com.example.slidingpanel;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {

	private List<Fruit> list;
	private Context context;

	public MenuAdapter(final Context context, final List<Fruit> list) {
		super();
		this.context = context;
		this.list = list;
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
			convertView = LayoutInflater.from(this.context).inflate(R.layout.menu_item, parent, false);
		}

//		convertView.setBackgroundColor(Color.parseColor("#111111"));
//		if (convertView.isSelected()) {
//			convertView.setBackgroundColor(Color.parseColor("#444444"));
//		}
		
		TextView textView = ViewHolder.get(convertView, R.id.menu_item_text);
		textView.setText(this.list.get(position).getName());

		return convertView;
	}

}
