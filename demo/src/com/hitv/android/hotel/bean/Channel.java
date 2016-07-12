package com.hitv.android.hotel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="channel", type=Channel.class)
public class Channel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6552324709956971037L;
	private int channelId;
	private int mainChannelId;
	private int channelOrder;
	private String channelName;
	private String tvThumbnailUrl;
	private String tvThumbnailLocalUrl;
	private String mobileThumbnailUrl;
	private int locationId;
	private int openType;
	private String openId;
	private int isDefault;
	private int hasRelCategory;
	private int hasRelContent;
	private int contentSize;
	private String childChannelKey;
	private String templateType;
	private List<Background> backgrounds;
	public int getChannelId() {
		return channelId;
	}
	
	@XmlAnnotation(name="channelId", type=Integer.class)
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getMainChannelId() {
		return mainChannelId;
	}
	
	@XmlAnnotation(name="mainChannelId", type=Integer.class)
	public void setMainChannelId(int mainChannelId) {
		this.mainChannelId = mainChannelId;
	}
	public int getChannelOrder() {
		return channelOrder;
	}
	
	@XmlAnnotation(name="channelOrder", type=Integer.class)
	public void setChannelOrder(int channelOrder) {
		this.channelOrder = channelOrder;
	}
	public String getChannelName() {
		return channelName;
	}
	
	@XmlAnnotation(name="channelName", type=String.class)
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	public int getLocationId() {
		return locationId;
	}
	
	@XmlAnnotation(name="locationId", type=Integer.class)
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getOpenType() {
		return openType;
	}
	
	@XmlAnnotation(name="openType", type=Integer.class)
	public void setOpenType(int openType) {
		this.openType = openType;
	}
	public String getOpenId() {
		return openId;
	}
	
	@XmlAnnotation(name="openId", type=String.class)
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getIsDefault() {
		return isDefault;
	}
	
	@XmlAnnotation(name="isDefault", type=Integer.class)
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public int getHasRelCategory() {
		return hasRelCategory;
	}
	
	@XmlAnnotation(name="hasRelCategory", type=Integer.class)
	public void setHasRelCategory(int hasRelCategory) {
		this.hasRelCategory = hasRelCategory;
	}
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
	public String getChildChannelKey() {
		return childChannelKey;
	}
	
	@XmlAnnotation(name="childChannelKey", type=String.class)
	public void setChildChannelKey(String childChannelKey) {
		this.childChannelKey = childChannelKey;
	}
	public List<Background> getBackgrounds() {
		return backgrounds;
	}
	
	@XmlAnnotation(name="backgrounds", type=ArrayList.class, target=Background.class)
	public void setBackgrounds(List<Background> backgrounds) {
		this.backgrounds = backgrounds;
	}

	public String getTemplateType() {
		return templateType;
	}

	@XmlAnnotation(name="templateType", type=String.class)
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
}
