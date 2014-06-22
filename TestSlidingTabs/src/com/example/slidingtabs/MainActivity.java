package com.example.slidingtabs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		ResultSearch fragment = new ResultSearch();
		transaction.replace(R.id.content, fragment);
		transaction.commit();
	}

}
