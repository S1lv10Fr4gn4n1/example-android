package com.example.slidingpanel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

	private Fruit detail;

	public DetailFragment(final Fruit detail) {
		super();
		this.detail = detail;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detail_fragment, container, false);
		this.initComponents(view);
		return view;
	}

	private void initComponents(final View view) {
		TextView text = (TextView) view.findViewById(R.id.detail_fragment_text);
		text.setText(this.detail.getName());
		
		ImageView image = (ImageView) view.findViewById(R.id.detail_fragment_image);
		image.setImageResource(this.detail.getImageResourse());
	}

}
