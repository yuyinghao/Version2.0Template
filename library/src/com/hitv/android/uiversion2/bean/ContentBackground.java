package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentBackground", type=ContentBackground.class)
public class ContentBackground implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -137148148106666478L;
	private String contentBackgroundId;
	private String backgroundUrl;
	private String backgroundLocalUrl;
	private int deviceType;
	private int order;
	public String getContentBackgroundId() {
		return contentBackgroundId;
	}
	
	@XmlAnnotation(name="contentBackgroundId", type=String.class)
	public void setContentBackgroundId(String contentBackgroundId) {
		this.contentBackgroundId = contentBackgroundId;
	}
	public String getBackgroundUrl() {
		return backgroundUrl;
	}
	
	@XmlAnnotation(name="backgroundUrl", type=String.class)
	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}
	
	
	public String getBackgroundLocalUrl() {
		return backgroundLocalUrl;
	}

	@XmlAnnotation(name="backgroundLocalUrl", type=String.class)
	public void setBackgroundLocalUrl(String backgroundLocalUrl) {
		this.backgroundLocalUrl = backgroundLocalUrl;
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
