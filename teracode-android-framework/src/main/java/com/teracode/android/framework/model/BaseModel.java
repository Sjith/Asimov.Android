package com.teracode.android.framework.model;

import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Fernando Perez
 */
public abstract class BaseModel<T extends BaseView> implements Model {

	protected T view;

	/**
	 * @param view
	 */
	public BaseModel(T view) {
		this.view = view;
	}

	/**
	 * @see com.teracode.android.framework.model.Model#init(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	public final void init(ValueModel valueModel) {
		this.view.doStartInitModel();
		try {
			this.doInit(valueModel);
			this.view.doStartSuccessfullInitModel();
		} catch (AndroidException e) {
			this.view.doStartFailureInitModel(e);
		}
	}

	/**
	 * @param valueModel
	 *            {@link ValueModel}
	 */
	protected abstract void doInit(ValueModel valueModel);

}
