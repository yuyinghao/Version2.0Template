package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name = "recommendPlaceBackground", type = RecommendPlaceBackground.class)
public class RecommendPlaceBackground implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1095557781364943201L;
	private String recommendPlaceBackgroundId;
	private String backgroundUrl;
	private String backgroundLocalUrl;
	private String deviceType;
	private String order;

	public String getRecommendPlaceBackgroundId() {
		return recommendPlaceBackgroundId;
	}

	@XmlAnnotation(name = "recommendPlaceBackgroundId", type = String.class)
	public void setRecommendPlaceBackgroundId(String recommendPlaceBackgroundId) {
		this.recommendPlaceBackgroundId = recommendPlaceBackgroundId;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	@XmlAnnotation(name = "backgroundUrl", type = String.class)
	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getBackgroundLocalUrl() {
		return backgroundLocalUrl;
	}

	@XmlAnnotation(name = "backgroundLocalUrl", type = String.class)
	public void setBackgroundLocalUrl(String backgroundLocalUrl) {
		this.backgroundLocalUrl = backgroundLocalUrl;
	}

	public String getDeviceType() {
		return deviceType;
	}

	@XmlAnnotation(name = "deviceType", type = String.class)
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOrder() {
		return order;
	}

	@XmlAnnotation(name = "order", type = String.class)
	public void setOrder(String order) {
		this.order = order;
	}

}
