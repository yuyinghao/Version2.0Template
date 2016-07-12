package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="category", type=ContentCategory.class)
public class ContentCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2844981977295466793L;
	private String categoryId;
	private String categoryName;
	
	public String getCategoryId() {
		return categoryId;
	}
	
	@XmlAnnotation(name="categoryId", type=String.class)
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	@XmlAnnotation(name="categoryName", type=String.class)
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
