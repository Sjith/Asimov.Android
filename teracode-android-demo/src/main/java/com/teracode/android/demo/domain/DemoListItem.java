package com.teracode.android.demo.domain;

public class DemoListItem {

	private String name;

	public DemoListItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}