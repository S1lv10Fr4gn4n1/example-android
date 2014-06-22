package com.example.slidingtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTest extends Fragment {
	
	public FragmentTest() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment, container, false);
		TextView textview = (TextView) view.findViewById(R.id.fragment_test_text);
		
		if (getArguments() != null) {
			textview.setText(getArguments().getString("text"));
		} 
		
		return view;
	}

}
