package com.teracode.android.demo.activity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.teracode.android.common.util.ToastUtil;
import com.teracode.android.demo.domain.DemoListItem;
import com.teracode.android.demo.model.ListModel;
import com.teracode.android.framework.R;
import com.teracode.android.framework.activity.BaseListActivity;

/**
 * @author Luciano Rey
 */
public class ListActivity extends BaseListActivity<DemoListItem, ListModel> implements
		com.teracode.android.demo.view.ListView {

	/**
	 * @see com.teracode.android.framework.activity.BaseListActivity#createModel()
	 */
	@Override
	protected ListModel createModel() {
		return new ListModel(this);
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseListActivity#getLayoutResourceId()
	 */
	@Override
	protected int getLayoutResourceId() {
		return R.layout.simple_list_activity;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseListActivity#findListView()
	 */
	@Override
	protected ListView findListView() {
		return (ListView) this.findViewById(android.R.id.list);
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseListActivity#getMoreElementsView()
	 */
	@Override
	protected View getMoreElementsView() {
		Button button = new Button(this);
		button.setText(this.getString(com.teracode.android.demo.R.string.more_elements));
		return button;
	}

	/**
	 * @see com.teracode.android.framework.activity.BaseListActivity#makeListAdapter()
	 */
	@Override
	protected ArrayAdapter<DemoListItem> makeListAdapter() {
		return new ArrayAdapter<DemoListItem>(this, android.R.layout.simple_list_item_1);
	}

	@Override
	public void touchElement(Object element) {
		ToastUtil.show(this.getString(com.teracode.android.demo.R.string.item_clicked));
	};

}
