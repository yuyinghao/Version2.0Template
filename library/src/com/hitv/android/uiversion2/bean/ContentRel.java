package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentRel", type=ContentRel.class)
public class ContentRel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1490235869749082362L;
	private String contentRelId;
	private int contentRelType;
	private int order;
	public String getContentRelId() {
		return contentRelId;
	}
	
	@XmlAnnotation(name="contentRelId", type=String.class)
	public void setContentRelId(String contentRelId) {
		this.contentRelId = contentRelId;
	}
	public int getContentRelType() {
		return contentRelType;
	}
	
	@XmlAnnotation(name="contentRelType", type=Integer.class)
	public void setContentRelType(int contentRelType) {
		this.contentRelType = contentRelType;
	}
	public int getOrder() {
		return order;
	}
	
	@XmlAnnotation(name="order", type=Integer.class)
	public void setOrder(int order) {
		this.order = order;
	}

	
}
