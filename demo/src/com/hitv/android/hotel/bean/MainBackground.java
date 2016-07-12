package com.hitv.android.hotel.bean;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="mainBackground", type=MainBackground.class)
public class MainBackground {
	
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
