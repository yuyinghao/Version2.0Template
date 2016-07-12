package com.hitv.android.hotel.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="background", type=Background.class)
public class Background implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3424599499730779085L;
	private String backgroundUrl;
	private String backgroundLocalUrl;
	private int deviceType;
	private int backgroundOrder;
	
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

	public int getBackgroundOrder() {
		return backgroundOrder;
	}

	@XmlAnnotation(name="backgroundOrder", type=Integer.class)
	public void setBackgroundOrder(int backgroundOrder) {
		this.backgroundOrder = backgroundOrder;
	}
	
}
