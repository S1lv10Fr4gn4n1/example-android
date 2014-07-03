package com.example.listvieweffects;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListFragment extends Fragment {

	private ListView listview;
	private ListFruitAdapter adapter;
	private List<Fruit> listFruit;

	private int mode = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.setHasOptionsMenu(true);

		View view = inflater.inflate(R.layout.list, container, false);

		this.listFruit = new ArrayList<Fruit>();
		listFruit.add(new Fruit("Avocado", R.drawable.avocado));
		listFruit.add(new Fruit("Orange", R.drawable.orange));
		listFruit.add(new Fruit("Banada", R.drawable.banana));
		listFruit.add(new Fruit("Strawberry", R.drawable.strawberry));
		listFruit.add(new Fruit("Apple", R.drawable.apple));
		listFruit.add(new Fruit("Lemon", R.drawable.lemon));
		listFruit.add(new Fruit("Watermelon", R.drawable.watermelon));
		listFruit.add(new Fruit("Grapes", R.drawable.grapes));
		listFruit.add(new Fruit("Pear", R.drawable.pear));
		listFruit.add(new Fruit("Cherry", R.drawable.cherry));
		listFruit.add(new Fruit("Peach", R.drawable.peach));
		listFruit.add(new Fruit("Pineapple", R.drawable.pineapple));
		listFruit.add(new Fruit("Papaya", R.drawable.papaya));
		this.adapter = new ListFruitAdapter(getActivity(), listFruit);

		this.listview = (ListView) view.findViewById(R.id.list_listview);
		registerForContextMenu(this.listview);

		// CardUI-like animation adapter using listviewanimations library
//        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(this.adapter);
//        swingBottomInAnimationAdapter.setListView(this.listview);
//        this.listview.setAdapter(swingBottomInAnimationAdapter);
		this.listview.setAdapter(this.adapter);
		this.listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), "Hello " + listFruit.get(position).getName(), Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		menu.add(Menu.NONE, 1, 0, "TranslateAnimation1");
		menu.add(Menu.NONE, 2, 0, "TranslateAnimation2");
		menu.add(Menu.NONE, 3, 0, "ScaleAnimation");
		menu.add(Menu.NONE, 4, 0, "fade_in");
		menu.add(Menu.NONE, 5, 0, "hyper_space_in");
		menu.add(Menu.NONE, 6, 0, "hyper_space_out");
		menu.add(Menu.NONE, 7, 0, "wave_scale");
		menu.add(Menu.NONE, 8, 0, "push_left_in");
		menu.add(Menu.NONE, 9, 0, "push_left_out");
		menu.add(Menu.NONE, 10, 0, "push_up_in");
		menu.add(Menu.NONE, 11, 0, "push_up_out");
		menu.add(Menu.NONE, 12, 0, "shake");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mode = item.getItemId();
		return super.onOptionsItemSelected(item);
	}

	private class ListFruitAdapter extends BaseAdapter {
		private List<Fruit> list;
		private Context context;
		private DisplayMetrics metrics;

		public ListFruitAdapter(final Context context, final List<Fruit> list) {
			super();
			this.list = list;
			this.context = context;

			this.metrics = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
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

			Fruit fruit = this.list.get(position);

			TextView textView = ViewHolder.get(convertView, R.id.list_item_text);
			textView.setText(fruit.getName());

			ImageView image = ViewHolder.get(convertView, R.id.list_item_image);
			image.setImageResource(fruit.getImageResourse());
			
			// http://karnshah8890.blogspot.com.br/2013/04/listview-animation-tutorial.html
			Animation animation = null;
			switch (mode) {
				case 1:
					animation = new TranslateAnimation(metrics.widthPixels / 2, 0, 0, 0);
					break;

				case 2:
					animation = new TranslateAnimation(0, 0, metrics.heightPixels, 0);
					break;

				case 3:
					animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0, (float) 1.0);
					break;

				case 4:
					animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
					break;
				case 5:
					animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_in);
					break;
				case 6:
					animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out);
					break;
				case 7:
					animation = AnimationUtils.loadAnimation(context, R.anim.wave_scale);
					break;
				case 8:
					animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
					break;
				case 9:
					animation = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
					break;
				case 10:
					animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
					break;
				case 11:
					animation = AnimationUtils.loadAnimation(context, R.anim.push_up_out);
					break;
				case 12:
					animation = AnimationUtils.loadAnimation(context, R.anim.shake);
					break;
			}

			animation.setDuration(500);
			convertView.startAnimation(animation);
			animation = null;

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
