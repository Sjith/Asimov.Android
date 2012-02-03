package com.teracode.android.framework.domain;

/**
 * @author Luciano Rey
 */
public class BusinessObject {

	private Long id;
	
	/**
	 * @param id
	 */
	public BusinessObject(Long id) {
		this.id = id;
	}

	/**
	 * @return id of business object.
	 */
	public Long getId() {
		return this.id;
	}
}
