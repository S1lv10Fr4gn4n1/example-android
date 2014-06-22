package com.example.slidingtabs.slidingtabs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.slidingtabs.FragmentTest;

public class SlidingTabContent {

	private int indicatorColor = Color.GRAY;
	private int dividerColor = Color.GRAY;
	private Class<FragmentTest> clazz;
	private String title;
	private Bundle arguments;

	public SlidingTabContent(Class<FragmentTest> clazz, Bundle arguments, String title, int indicatorColor, int dividerColor) {
		this(clazz, arguments, title);
		this.indicatorColor = indicatorColor;
		this.dividerColor = dividerColor;
	}

	public SlidingTabContent(Class<FragmentTest> clazz, Bundle arguments, String title) {
		super();
		this.clazz = clazz;
		this.arguments = arguments;
		this.title = title;
	}

	public Fragment createFragment() {
		try {
			FragmentTest newInstance = this.clazz.newInstance();
			newInstance.setArguments(this.arguments);
			return newInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CharSequence getTitle() {
		return this.title;
	}

	public int getIndicatorColor() {
		return this.indicatorColor;
	}

	public int getDividerColor() {
		return this.dividerColor;
	}

}
