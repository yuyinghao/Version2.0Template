package com.hitv.android.hotel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="category", type=Category.class)
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7895878312881481175L;
	private int contentCategoryId;
	private int categoryOrder;
	private String categoryName;
	private String categoryDesc;
	private String tvThumbnailUrl;
	private String tvThumbnailLocalUrl;
	private String mobileThumbnailUrl;
	private String locationId;
	private String openType;
	private String openId;
	private int hasChild;
	private int isDefault;
	private int hasRelContent;
	private int contentSize;
	private String firstLevelCategoryName;
	private List<Background> backgrounds;
	
	
	public int getHasRelContent() {
		return hasRelContent;
	}

	@XmlAnnotation(name="hasRelContent", type=Integer.class)
	public void setHasRelContent(int hasRelContent) {
		this.hasRelContent = hasRelContent;
	}

	public int getContentSize() {
		return contentSize;
	}

	@XmlAnnotation(name="contentSize", type=Integer.class)
	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}

	public int getContentCategoryId() {
		return contentCategoryId;
	}
	
	@XmlAnnotation(name="contentCategoryId", type=Integer.class)
	public void setContentCategoryId(int contentCategoryId) {
		this.contentCategoryId = contentCategoryId;
	}
	public int getCategoryOrder() {
		return categoryOrder;
	}
	
	@XmlAnnotation(name="categoryOrder", type=Integer.class)
	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	public String getCategoryName() {
		return categoryName;
	}
	
	@XmlAnnotation(name="categoryName", type=String.class)
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	
	@XmlAnnotation(name="categoryDesc", type=String.class)
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getTvThumbnailUrl() {
		return tvThumbnailUrl;
	}
	
	@XmlAnnotation(name="tvThumbnailUrl", type=String.class)
	public void setTvThumbnailUrl(String tvThumbnailUrl) {
		this.tvThumbnailUrl = tvThumbnailUrl;
	}
	
	public String getTvThumbnailLocalUrl() {
		return tvThumbnailLocalUrl;
	}

	@XmlAnnotation(name="tvThumbnailLocalUrl", type=String.class)
	public void setTvThumbnailLocalUrl(String tvThumbnailLocalUrl) {
		this.tvThumbnailLocalUrl = tvThumbnailLocalUrl;
	}

	public String getMobileThumbnailUrl() {
		return mobileThumbnailUrl;
	}
	
	@XmlAnnotation(name="mobileThumbnailUrl", type=String.class)
	public void setMobileThumbnailUrl(String mobileThumbnailUrl) {
		this.mobileThumbnailUrl = mobileThumbnailUrl;
	}
	public String getLocationId() {
		return locationId;
	}
	
	@XmlAnnotation(name="locationId", type=String.class)
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getOpenType() {
		return openType;
	}
	
	@XmlAnnotation(name="openType", type=String.class)
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getOpenId() {
		return openId;
	}
	
	@XmlAnnotation(name="openType", type=String.class)
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getHasChild() {
		return hasChild;
	}
	
	@XmlAnnotation(name="hasChild", type=Integer.class)
	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}
	public int getIsDefault() {
		return isDefault;
	}
	
	@XmlAnnotation(name="isDefault", type=Integer.class)
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getFirstLevelCategoryName() {
		return firstLevelCategoryName;
	}
	
	@XmlAnnotation(name="firstLevelCategoryName", type=String.class)
	public void setFirstLevelCategoryName(String firstLevelCategoryName) {
		this.firstLevelCategoryName = firstLevelCategoryName;
	}
	public List<Background> getBackgrounds() {
		return backgrounds;
	}
	
	@XmlAnnotation(name="backgrounds", type=ArrayList.class, target=Background.class)
	public void setBackgrounds(List<Background> backgrounds) {
		this.backgrounds = backgrounds;
	}
	
	
	
}
