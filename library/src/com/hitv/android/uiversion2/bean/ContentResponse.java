package com.hitv.android.uiversion2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="getContentResponse", type=ContentResponse.class)
public class ContentResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227640349898109526L;
	private Result result;
	private PageInfo pageInfo;
	private List<Content> contents;
	public Result getResult() {
		return result;
	}
	
	@XmlAnnotation(name="result", type=Result.class, target=Result.class)
	public void setResult(Result result) {
		this.result = result;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	@XmlAnnotation(name="pageInfo", type=PageInfo.class, target=PageInfo.class)
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public List<Content> getContents() {
		return contents;
	}
	
	@XmlAnnotation(name="contents", type=ArrayList.class, target=Content.class)
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
}
