package com.teracode.android.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class AsimovInfoRepository {

	private static AsimovInfoRepository repository = new AsimovInfoRepository();
	private List<DemoListItem> demoListItems = new ArrayList<DemoListItem>();

	private AsimovInfoRepository() {
		initialize();
	}

	public static AsimovInfoRepository get() {
		return repository;
	}

	public List<DemoListItem> getAll() {
		return demoListItems;
	}

	private void initialize() {
		for (int i = 0; i < 8; i++) {
			demoListItems.add(new DemoListItem("DemoListItem " + i));
		}
	}
}
