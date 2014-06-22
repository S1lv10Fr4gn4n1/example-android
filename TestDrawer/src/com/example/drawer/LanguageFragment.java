package com.example.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LanguageFragment extends Fragment {

	public final static String TITLE = "TITLE";

	public LanguageFragment() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.language_fragment, container, false);

		Bundle bundle = getArguments();
		if (bundle != null) {
			String language = bundle.getString(TITLE);
			if (language != null && !language.trim().isEmpty()) {
				TextView text = (TextView) view.findViewById(R.id.fragment_main_text);
				text.setText(language);
			}
		}

		return view;
	}
}