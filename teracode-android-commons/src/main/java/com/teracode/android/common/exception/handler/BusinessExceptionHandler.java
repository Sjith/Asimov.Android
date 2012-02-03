package com.teracode.android.common.exception.handler;

import com.teracode.android.common.exception.BusinessException;
import com.teracode.android.common.util.ToastUtil;

/**
 * @author Fernando Perez
 */
public class BusinessExceptionHandler implements ExceptionHandler<BusinessException> {

	/**
	 * @see com.teracode.android.common.exception.handler.ExceptionHandler#handle(com.teracode.android.common.exception.AndroidException)
	 */
	@Override
	public void handle(BusinessException exception) {
		ToastUtil.showOnUIThread(exception.getMessage());		
	}
	
}
