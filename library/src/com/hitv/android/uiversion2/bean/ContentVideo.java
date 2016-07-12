package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentVideo", type=ContentVideo.class)
public class ContentVideo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 512568354126699708L;
	private String contentVideoId;
	private String description;
	private String tvVideoThumbnailUrl;
	private String tvVideoThumbnailLocalUrl;
	private String tvVideoUrl;
	private String tvVideoLocalUrl;
	private int deviceType;
	private int order;
	public String getContentVideoId() {
		return contentVideoId;
	}
	
	@XmlAnnotation(name="contentVideoId", type=String.class)
	public void setContentVideoId(String contentVideoId) {
		this.contentVideoId = contentVideoId;
	}
	public String getDescription() {
		return description;
	}

	@XmlAnnotation(name="description", type=String.class)
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTvVideoThumbnailUrl() {
		return tvVideoThumbnailUrl;
	}
	@XmlAnnotation(name="tvVideoThumbnailUrl", type=String.class)
	public void setTvVideoThumbnailUrl(String tvVideoThumbnailUrl) {
		this.tvVideoThumbnailUrl = tvVideoThumbnailUrl;
	}
	public String getTvVideoUrl() {
		return tvVideoUrl;
	}
	@XmlAnnotation(name="tvVideoUrl", type=String.class)
	public void setTvVideoUrl(String tvVideoUrl) {
		this.tvVideoUrl = tvVideoUrl;
	}
	public int getDeviceType() {
		return deviceType;
	}
	@XmlAnnotation(name="deviceType", type=Integer.class)
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getOrder() {
		return order;
	}
	@XmlAnnotation(name="order", type=Integer.class)
	public void setOrder(int order) {
		this.order = order;
	}

	public String getTvVideoThumbnailLocalUrl() {
		return tvVideoThumbnailLocalUrl;
	}

	@XmlAnnotation(name="tvVideoThumbnailLocalUrl", type=String.class)
	public void setTvVideoThumbnailLocalUrl(String tvVideoThumbnailLocalUrl) {
		this.tvVideoThumbnailLocalUrl = tvVideoThumbnailLocalUrl;
	}

	public String getTvVideoLocalUrl() {
		return tvVideoLocalUrl;
	}

	@XmlAnnotation(name="tvVideoLocalUrl", type=String.class)
	public void setTvVideoLocalUrl(String tvVideoLocalUrl) {
		this.tvVideoLocalUrl = tvVideoLocalUrl;
	}
}
