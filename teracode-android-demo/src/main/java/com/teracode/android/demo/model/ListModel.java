package com.teracode.android.demo.model;

import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.demo.domain.DemoListItem;
import com.teracode.android.demo.domain.DemoListItemRepository;
import com.teracode.android.demo.view.ListView;
import com.teracode.android.framework.model.BaseListModel;
import com.teracode.android.framework.model.ValueModel;

/**
 * @author Luciano Rey
 */
public class ListModel extends BaseListModel<DemoListItem, ListView> {

	/**
	 * @param view
	 */
	public ListModel(ListView view) {
		super(view);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseModel#doInit(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	protected void doInit(ValueModel valueModel) {
		this.setElements(DemoListItemRepository.get().getAll());
		ThreadUtil.sleep(3);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseListModel#handleOnElementSelected(java.lang.Object)
	 */
	@Override
	protected void handleOnElementSelected(DemoListItem element) {
		this.view.touchElement(element);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseListModel#doLoadMoreElements()
	 */
	@Override
	protected void doLoadMoreElements() {
		this.addElement(new DemoListItem("Un agregado"));
		ThreadUtil.sleep(2);
	}
}
