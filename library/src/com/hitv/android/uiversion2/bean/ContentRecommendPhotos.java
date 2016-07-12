package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentRecommendPhoto", type=ContentRecommendPhotos.class)
public class ContentRecommendPhotos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3413812487475097456L;
	private String contentRecommendPhotoId;
	private String contentRecommendPhotoUrl;
	private String contentRecommendPhotoLocalUrl;
	private String contentRecommendPhotoLanguage;
	private int deviceType;
	private int order;
	
	public String getContentRecommendPhotoLanguage() {
		return contentRecommendPhotoLanguage;
	}

	@XmlAnnotation(name="contentRecommendPhotoLanguage", type=String.class)
	public void setContentRecommendPhotoLanguage(String contentRecommendPhotoLanguage) {
		this.contentRecommendPhotoLanguage = contentRecommendPhotoLanguage;
	}
	
	public String getContentRecommendPhotoId() {
		return contentRecommendPhotoId;
	}
	
	@XmlAnnotation(name="contentRecommendPhotoId", type=String.class)
	public void setContentRecommendPhotoId(String contentRecommendPhotoId) {
		this.contentRecommendPhotoId = contentRecommendPhotoId;
	}
	public String getContentRecommendPhotoUrl() {
		return contentRecommendPhotoUrl;
	}

	@XmlAnnotation(name="contentRecommendPhotoUrl", type=String.class)
	public void setContentRecommendPhotoUrl(String contentRecommendPhotoUrl) {
		this.contentRecommendPhotoUrl = contentRecommendPhotoUrl;
	}
	
	public String getContentRecommendPhotoLocalUrl() {
		return contentRecommendPhotoLocalUrl;
	}

	@XmlAnnotation(name="contentRecommendPhotoLocalUrl", type=String.class)
	public void setContentRecommendPhotoLocalUrl(
			String contentRecommendPhotoLocalUrl) {
		this.contentRecommendPhotoLocalUrl = contentRecommendPhotoLocalUrl;
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
	
	

}
