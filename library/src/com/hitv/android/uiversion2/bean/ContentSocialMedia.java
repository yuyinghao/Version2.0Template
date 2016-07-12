package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentSocialMedia", type=ContentSocialMedia.class)
public class ContentSocialMedia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642494398382518647L;
	private int scoialMediaId;
	private String scoialMediaTvThumbnailUrl;
	private String scoialMediaTvThumbnaiLocallUrl;
	private String scoialMediaTvCName;
	private String scoialMediaTvEName;
	private String qrCodeUrl;
	private String qrCodeLocalUrl;
	private String entry;
	private int entryType;
	public int getScoialMediaId() {
		return scoialMediaId;
	}
	
	@XmlAnnotation(name="scoialMediaId", type=Integer.class)
	public void setScoialMediaId(int scoialMediaId) {
		this.scoialMediaId = scoialMediaId;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	
	@XmlAnnotation(name="qrCodeUrl", type=String.class)
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	
	public String getQrCodeLocalUrl() {
		return qrCodeLocalUrl;
	}
	
	@XmlAnnotation(name="qrCodeLocalUrl", type=String.class)
	public void setQrCodeLocalUrl(String qrCodeLocalUrl) {
		this.qrCodeLocalUrl = qrCodeLocalUrl;
	}

	public String getEntry() {
		return entry;
	}
	
	@XmlAnnotation(name="entry", type=String.class)
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public int getEntryType() {
		return entryType;
	}
	
	@XmlAnnotation(name="entryType", type=Integer.class)
	public void setEntryType(int entryType) {
		this.entryType = entryType;
	}

	public String getScoialMediaTvThumbnailUrl() {
		return scoialMediaTvThumbnailUrl;
	}

	@XmlAnnotation(name="scoialMediaTvThumbnailUrl", type=String.class)
	public void setScoialMediaTvThumbnailUrl(String scoialMediaTvThumbnailUrl) {
		this.scoialMediaTvThumbnailUrl = scoialMediaTvThumbnailUrl;
	}

	public String getScoialMediaTvThumbnaiLocallUrl() {
		return scoialMediaTvThumbnaiLocallUrl;
	}

	@XmlAnnotation(name="scoialMediaTvThumbnaiLocallUrl", type=String.class)
	public void setScoialMediaTvThumbnaiLocallUrl(
			String scoialMediaTvThumbnaiLocallUrl) {
		this.scoialMediaTvThumbnaiLocallUrl = scoialMediaTvThumbnaiLocallUrl;
	}

	public String getScoialMediaTvCName() {
		return scoialMediaTvCName;
	}

	@XmlAnnotation(name="scoialMediaTvCName", type=String.class)
	public void setScoialMediaTvCName(String scoialMediaTvCName) {
		this.scoialMediaTvCName = scoialMediaTvCName;
	}

	public String getScoialMediaTvEName() {
		return scoialMediaTvEName;
	}

	@XmlAnnotation(name="scoialMediaTvEName", type=String.class)
	public void setScoialMediaTvEName(String scoialMediaTvEName) {
		this.scoialMediaTvEName = scoialMediaTvEName;
	}
	
}
