package com.example.slidingpanel;

public class Fruit {
	private String name;
	private int imageResourse;

	public Fruit(String name, int imageResourse) {
		super();
		this.name = name;
		this.imageResourse = imageResourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImageResourse() {
		return imageResourse;
	}

	public void setImageResourse(int imageResourse) {
		this.imageResourse = imageResourse;
	}

}
