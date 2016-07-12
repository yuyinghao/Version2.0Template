package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentPhoto", type=ContentPhotos.class)
public class ContentPhotos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3764651007136331492L;
	private String contentPhotoId;
	private String photoUrl;
	private String photoLocalUrl;
	private String description;
	private int deviceType;
	private int order;
	public String getContentPhotoId() {
		return contentPhotoId;
	}
	
	@XmlAnnotation(name="contentPhotoId", type=String.class)
	public void setContentPhotoId(String contentPhotoId) {
		this.contentPhotoId = contentPhotoId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	@XmlAnnotation(name="photoUrl", type=String.class)
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getPhotoLocalUrl() {
		return photoLocalUrl;
	}

	@XmlAnnotation(name="photoLocalUrl", type=String.class)
	public void setPhotoLocalUrl(String photoLocalUrl) {
		this.photoLocalUrl = photoLocalUrl;
	}

	public String getDescription() {
		return description;
	}
	
	@XmlAnnotation(name="description", type=String.class)
	public void setDescription(String description) {
		this.description = description;
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
