package com.teracode.android.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class DemoListItemRepository {

	private static DemoListItemRepository repository = new DemoListItemRepository();
	private List<DemoListItem> demoListItems = new ArrayList<DemoListItem>();

	private DemoListItemRepository() {
		initialize();
	}

	public static DemoListItemRepository get() {
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
