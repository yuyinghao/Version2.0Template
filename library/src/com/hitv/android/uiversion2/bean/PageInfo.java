package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="pageInfo", type=PageInfo.class)
public class PageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2222957300545971392L;
	private int pageSize;
	private int currentPageNo;
	private int totalResult;
	private int totalPage;
	public int getPageSize() {
		return pageSize;
	}
	
	@XmlAnnotation(name="pageSize", type=Integer.class)
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	
	@XmlAnnotation(name="currentPageNo", type=Integer.class)
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getTotalResult() {
		return totalResult;
	}
	
	@XmlAnnotation(name="totalResult", type=Integer.class)
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getTotalPage() {
		return totalPage;
	}
	
	@XmlAnnotation(name="totalPage", type=Integer.class)
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
