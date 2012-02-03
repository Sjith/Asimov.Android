package com.teracode.android.framework.model;

import java.util.List;

import com.google.inject.internal.Lists;
import com.teracode.android.common.exception.AndroidException;
import com.teracode.android.framework.view.BaseListView;

/**
 * @author Luciano Rey
 */
public abstract class BaseListModel<E extends Object, T extends BaseListView> extends BaseModel<T> {

	private List<E> elements = Lists.newArrayList();

	/**
	 * @param view
	 */
	public BaseListModel(T view) {
		super(view);
	}

	// ********************************************
	// ***
	// ********************************************

	public final void loadMoreElements() {
		this.view.doStartLoadMoreElements();
		try {
			this.doLoadMoreElements();
			this.view.doStartSuccessfullLoadMoreElements();
		} catch (AndroidException e) {
			this.view.doStartFailureLoadMoreElements(e);
		}
	}

	// ********************************************
	// *** Configuration's
	// ********************************************

	/**
	 * @param element
	 *            selected.
	 */
	public final void onElementSelected(E element) {
		this.handleOnElementSelected(element);
	}

	/**
	 * @param elements
	 */
	public final void setElements(List<E> elements) {
		this.elements = elements;
	}

	/**
	 * @param element
	 */
	public final void addElement(E element) {
		this.elements.add(element);
	}

	// ********************************************
	// *** Accessor's
	// ********************************************

	/**
	 * @return the elements
	 */
	public List<E> getElements() {
		return elements;
	}

	// ********************************************
	// *** Abstract Method's
	// ********************************************

	/**
	 * @param element
	 */
	protected abstract void handleOnElementSelected(E element);

	// ********************************************
	// *** Template method's
	// ********************************************

	protected void doLoadMoreElements() {
		// nothing...
	}
}
