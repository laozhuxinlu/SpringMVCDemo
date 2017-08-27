package com.base.page;

import java.util.List;

/**
 * ExtJs分页用Bean.
 *
 * @author weidong
 * 
 */
public class Page {
	/** 总记录数 */
	private int total;

	/** 分页结果 */
	private List data;

	/** 开始页码 */
	private int start;

	/** 每页多少 */
	private int limit;

	/** 成功与否 */
	private boolean success = true;
	
	private String message;
	
	/**
	 * 是否分页标志(默认分页).
	 */
	private boolean pageFlag = true;

	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Page [total=" + total + ", data=" + data + ", start=" + start
				+ ", limit=" + limit + ", success=" + success + ", message="
				+ message + ", pageFlag=" + pageFlag + "]";
	}
	
}
