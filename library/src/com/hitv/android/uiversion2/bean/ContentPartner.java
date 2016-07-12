package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="contentPartner", type=ContentPartner.class)
public class ContentPartner implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6990838507712728008L;
	private int partnerId;
	private String tvPartnerUrl;
	private String tvPartnerLocalUrl;
	private String mobilePartnerUrl;
	private String webSite;
	private String partnerCName;
	private String partnerEName;
	private String partnerIntro;
	private String qrCodeUrl;
	private String qrCodeLocalUrl;
	public int getPartnerId() {
		return partnerId;
	}
	
	@XmlAnnotation(name="partnerId", type=Integer.class)
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getTvPartnerUrl() {
		return tvPartnerUrl;
	}
	
	@XmlAnnotation(name="tvPartnerUrl", type=String.class)
	public void setTvPartnerUrl(String tvPartnerUrl) {
		this.tvPartnerUrl = tvPartnerUrl;
	}
	
	public String getTvPartnerLocalUrl() {
		return tvPartnerLocalUrl;
	}

	@XmlAnnotation(name="tvPartnerLocalUrl", type=String.class)
	public void setTvPartnerLocalUrl(String tvPartnerLocalUrl) {
		this.tvPartnerLocalUrl = tvPartnerLocalUrl;
	}

	public String getMobilePartnerUrl() {
		return mobilePartnerUrl;
	}
	
	@XmlAnnotation(name="mobilePartnerUrl", type=String.class)
	public void setMobilePartnerUrl(String mobilePartnerUrl) {
		this.mobilePartnerUrl = mobilePartnerUrl;
	}
	public String getWebSite() {
		return webSite;
	}
	
	@XmlAnnotation(name="webSite", type=String.class)
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getPartnerCName() {
		return partnerCName;
	}
	
	@XmlAnnotation(name="partnerCName", type=String.class)
	public void setPartnerCName(String partnerCName) {
		this.partnerCName = partnerCName;
	}
	public String getPartnerEName() {
		return partnerEName;
	}
	
	@XmlAnnotation(name="partnerEName", type=String.class)
	public void setPartnerEName(String partnerEName) {
		this.partnerEName = partnerEName;
	}

	public String getPartnerIntro() {
		return partnerIntro;
	}

	@XmlAnnotation(name="partnerIntro", type=String.class)
	public void setPartnerIntro(String partnerIntro) {
		this.partnerIntro = partnerIntro;
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

}
