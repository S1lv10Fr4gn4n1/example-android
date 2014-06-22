package com.example.drawer;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// TODO http://www.piwai.info/android-adapter-good-practices/
public class LanguageAdapter extends BaseAdapter {
	private String[] languages;
	private Context context;

	public LanguageAdapter(final Context context, final String[] languages) {
		this.context = context;
		this.languages = languages;
	}

	public void updateLanguages(final String[] languages) {
		this.languages = languages;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return languages.length;
	}

	@Override
	public Object getItem(int position) {
		return languages[position];
	}

	@Override
	public long getItemId(int position) {
		return languages[position].hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
		}

		convertView.setBackgroundColor(Color.parseColor("#111111"));
		if (convertView.isSelected()) {
			convertView.setBackgroundColor(Color.parseColor("#444444"));
		}

		TextView textview = ViewHolder.get(convertView, R.id.drawer_list_item_text);
		textview.setText(this.languages[position]);

		return convertView;
	}

	// http://www.piwai.info/android-adapter-good-practices/#Update
	public static class ViewHolder {
		// I added a generic return type to reduce the casting noise in client code
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