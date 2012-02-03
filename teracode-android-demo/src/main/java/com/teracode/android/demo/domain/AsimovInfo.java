package com.teracode.android.demo.domain;

public class AsimovInfo {

	private String name;

	public AsimovInfo(String name) {
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