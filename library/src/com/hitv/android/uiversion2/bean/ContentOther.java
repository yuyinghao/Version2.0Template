package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentOther", type=ContentOther.class)
public class ContentOther implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7527266964007827779L;
	private String contentOtherId;

	public String getContentOtherId() {
		return contentOtherId;
	}

	@XmlAnnotation(name="contentOtherId", type=String.class)
	public void setContentOtherId(String contentOtherId) {
		this.contentOtherId = contentOtherId;
	}
	
}
