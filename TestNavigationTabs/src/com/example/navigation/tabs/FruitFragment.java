package com.example.navigation.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FruitFragment extends Fragment {

	private Fruit fruit;

	public FruitFragment() {
		super();
	}

	public FruitFragment(final Fruit fruit) {
		super();
		this.fruit = fruit;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fruit_fragment, container, false);

		ImageView image = (ImageView) view.findViewById(R.id.fruit_fragment_image);
		image.setImageResource(this.fruit.getImageResourse());
		return view;
	}
}
