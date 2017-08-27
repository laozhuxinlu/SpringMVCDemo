package com.springMVC.controller;


import com.base.page.Page;
import com.base.util.ApplicationException;
import com.google.common.base.Strings;

public class BaseController {
	
	protected Page page=null;
	
	
	/**
	 * 检测参数值是否为空,为空自动扔异常.
	 */
	public void validateNullException(String value, String errorMsg) throws ApplicationException {
		if (Strings.isNullOrEmpty(value)) { // 值为空
			throw new ApplicationException(errorMsg);
		}
	}
}
