package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentAttr", type=ContentAttr.class)
public class ContentAttr implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 543624483583041446L;
	private int contentAttrId;
	private String contentAttrName;
	private String contentAttrValue;
	public int getContentAttrId() {
		return contentAttrId;
	}
	
	@XmlAnnotation(name="contentAttrId", type=Integer.class)
	public void setContentAttrId(int contentAttrId) {
		this.contentAttrId = contentAttrId;
	}
	public String getContentAttrName() {
		return contentAttrName;
	}
	
	@XmlAnnotation(name="contentAttrName", type=String.class)
	public void setContentAttrName(String contentAttrName) {
		this.contentAttrName = contentAttrName;
	}
	public String getContentAttrValue() {
		return contentAttrValue;
	}
	
	@XmlAnnotation(name="contentAttrValue", type=String.class)
	public void setContentAttrValue(String contentAttrValue) {
		this.contentAttrValue = contentAttrValue;
	}
	
	
}
