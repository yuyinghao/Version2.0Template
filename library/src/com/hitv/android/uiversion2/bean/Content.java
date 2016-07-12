package com.hitv.android.uiversion2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name="content", type=Content.class)
public class Content implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6892904866467564711L;
	private String contentId;
	private int contentType;
	private String contentCategory;
	private String displayType;
	private String cityId;
	private int locationId;
	private String tvLogoUrl;
	private String tvLogoLocalUrl;
	private String mobileLogoUrl;
	private String tvThumbnailUrl;
	private String tvThumbnailLocalUrl;
	private String mobileThumbnailUrl;
	private String website;
	private String telPhone;
	private String distance;
	private String distanceUnit;
	private String price;
	private String rate;
	private String iconUrl;
	private String iconLocalUrl;
	private String longitude;
	private String latitude;
	private String openType;
	private String openId;
	private String contentOrder;
	private String isRecommend;
	private String cityName;
	private String weatherSnapSource;
	private String visitCount;
	private String firstTitle;
	private String secondTitle;
	private String address;
	private String intro;
	private String special;
	private String remark;
	private String weight;
	private String businessHour;
	private String suggest;
	private String recommendIntro;
	private String supportDelivery;
	private String supportPurchase;
	private String firstLevelDisplayName;
	private String currencySymbol;
	
	private String qrLogoUrl;
	private String qrLogoLocalUrl;
	private String qrEnLogoUrl;
	private String qrEnLogoLocalUrl;
	private String qrTwLogoUrl;
	private String qrTwLogoLocalUrl;
	private String qrX;
	private String qrY;
	private String qrWidth;
	private String qrHeight;
	private String qrCode;
	private String qrEnCode;
	private String qrTwCode;
	
	private String price2;
	private String heatIndex;
	
	private String qrCtaEnText;
	private String qrCtaZhText;
	private String qrCtaTwText;
	private String qrEnText;
	private String qrZhText;
	private String qrTwText;
	
	private List<ContentAttr> contentAttrs;
	private List<ContentRel> contentRels;
	private List<ContentOther> contentOthers;
	private List<ContentBackground> contentBackgrounds;
	private List<ContentPhotos> contentPhotos;
	private List<ContentVideo> contentVideos;
	private List<ContentRecommendPhotos> contentRecommendPhotos;
	private List<ContentPartner> contentPartners;
	private List<ContentSocialMedia> contentSocialMedias;
	private List<ContentCategory> categorys;
	private boolean isShowLoading=false;
	
	public String getQrCtaEnText() {
		return qrCtaEnText;
	}

	@XmlAnnotation(name="qrCtaEnText", type=String.class)
	public void setQrCtaEnText(String qrCtaEnText) {
		this.qrCtaEnText = qrCtaEnText;
	}

	public String getQrCtaZhText() {
		return qrCtaZhText;
	}

	@XmlAnnotation(name="qrCtaZhText", type=String.class)
	public void setQrCtaZhText(String qrCtaZhText) {
		this.qrCtaZhText = qrCtaZhText;
	}

	public String getQrCtaTwText() {
		return qrCtaTwText;
	}

	@XmlAnnotation(name="qrCtaTwText", type=String.class)
	public void setQrCtaTwText(String qrCtaTwText) {
		this.qrCtaTwText = qrCtaTwText;
	}

	public String getQrEnText() {
		return qrEnText;
	}

	@XmlAnnotation(name="qrEnText", type=String.class)
	public void setQrEnText(String qrEnText) {
		this.qrEnText = qrEnText;
	}

	public String getQrZhText() {
		return qrZhText;
	}

	@XmlAnnotation(name="qrZhText", type=String.class)
	public void setQrZhText(String qrZhText) {
		this.qrZhText = qrZhText;
	}

	public String getQrTwText() {
		return qrTwText;
	}
	
	@XmlAnnotation(name="qrTwText", type=String.class)
	public void setQrTwText(String qrTwText) {
		this.qrTwText = qrTwText;
	}

	public boolean isShowLoading() {
		return isShowLoading;
	}

	public void setShowLoading(boolean isShowLoading) {
		this.isShowLoading = isShowLoading;
	}

	public String getContentId() {
		return contentId;
	}
	
	@XmlAnnotation(name="contentId", type=String.class)
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public int getContentType() {
		return contentType;
	}
	
	@XmlAnnotation(name="contentType", type=Integer.class)
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public String getContentCategory() {
		return contentCategory;
	}
	
	@XmlAnnotation(name="contentCategory", type=String.class)
	public void setContentCategory(String contentCategory) {
		this.contentCategory = contentCategory;
	}
	public String getDisplayType() {
		return displayType;
	}
	
	@XmlAnnotation(name="displayType", type=String.class)
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getCityId() {
		return cityId;
	}
	
	@XmlAnnotation(name="cityId", type=String.class)
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getLocationId() {
		return locationId;
	}
	
	@XmlAnnotation(name="locationId", type=Integer.class)
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getTvLogoUrl() {
		return tvLogoUrl;
	}
	
	@XmlAnnotation(name="tvLogoUrl", type=String.class)
	public void setTvLogoUrl(String tvLogoUrl) {
		this.tvLogoUrl = tvLogoUrl;
	}
	public String getMobileLogoUrl() {
		return mobileLogoUrl;
	}
	
	@XmlAnnotation(name="mobileLogoUrl", type=String.class)
	public void setMobileLogoUrl(String mobileLogoUrl) {
		this.mobileLogoUrl = mobileLogoUrl;
	}
	public String getTvThumbnailUrl() {
		return tvThumbnailUrl;
	}
	
	@XmlAnnotation(name="tvThumbnailUrl", type=String.class)
	public void setTvThumbnailUrl(String tvThumbnailUrl) {
		this.tvThumbnailUrl = tvThumbnailUrl;
	}
	public String getMobileThumbnailUrl() {
		return mobileThumbnailUrl;
	}
	
	@XmlAnnotation(name="mobileThumbnailUrl", type=String.class)
	public void setMobileThumbnailUrl(String mobileThumbnailUrl) {
		this.mobileThumbnailUrl = mobileThumbnailUrl;
	}
	public String getWebsite() {
		return website;
	}
	
	@XmlAnnotation(name="website", type=String.class)
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTelPhone() {
		return getDealWithString(telPhone);
	}
	
	@XmlAnnotation(name="telPhone", type=String.class)
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getDistance() {
		return distance;
	}
	
	@XmlAnnotation(name="distance", type=String.class)
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDistanceUnit() {
		return distanceUnit;
	}
	
	@XmlAnnotation(name="distanceUnit", type=String.class)
	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	public String getPrice() {
		return getDealWithString(price);
	}
	
	@XmlAnnotation(name="price", type=String.class)
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRate() {
		return rate;
	}
	
	@XmlAnnotation(name="rate", type=String.class)
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	
	@XmlAnnotation(name="iconUrl", type=String.class)
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getLongitude() {
		return longitude;
	}
	
	@XmlAnnotation(name="longitude", type=String.class)
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	
	@XmlAnnotation(name="latitude", type=String.class)
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getOpenType() {
		return openType;
	}
	
	@XmlAnnotation(name="openType", type=String.class)
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getOpenId() {
		return openId;
	}
	
	@XmlAnnotation(name="openId", type=String.class)
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getContentOrder() {
		return contentOrder;
	}
	
	@XmlAnnotation(name="contentOrder", type=String.class)
	public void setContentOrder(String contentOrder) {
		this.contentOrder = contentOrder;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	
	@XmlAnnotation(name="isRecommend", type=String.class)
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getCityName() {
		return cityName;
	}
	
	@XmlAnnotation(name="cityName", type=String.class)
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getWeatherSnapSource() {
		return weatherSnapSource;
	}
	
	@XmlAnnotation(name="weatherSnapSource", type=String.class)
	public void setWeatherSnapSource(String weatherSnapSource) {
		this.weatherSnapSource = weatherSnapSource;
	}
	public String getVisitCount() {
		return visitCount;
	}
	
	@XmlAnnotation(name="visitCount", type=String.class)
	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}
	public String getFirstTitle() {
		return getDealWithString(firstTitle);
	}
	
	@XmlAnnotation(name="firstTitle", type=String.class)
	public void setFirstTitle(String firstTitle) {
		this.firstTitle = firstTitle;
	}
	public String getSecondTitle() {
		return getDealWithString(secondTitle);
	}
	
	@XmlAnnotation(name="secondTitle", type=String.class)
	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}
	public String getAddress() {
		return getDealWithString(address);
	}
	
	@XmlAnnotation(name="address", type=String.class)
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntro() {
		return getDealWithString(intro);
	}
	
	@XmlAnnotation(name="intro", type=String.class)
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getSpecial() {
		return getDealWithString(special);
	}
	
	@XmlAnnotation(name="special", type=String.class)
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getRemark() {
		return getDealWithString(remark);
	}
	
	@XmlAnnotation(name="remark", type=String.class)
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWeight() {
		return weight;
	}
	
	@XmlAnnotation(name="weight", type=String.class)
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBusinessHour() {
		return getDealWithString(businessHour);
	}
	
	@XmlAnnotation(name="businessHour", type=String.class)
	public void setBusinessHour(String businessHour) {
		this.businessHour = businessHour;
	}
	public String getSuggest() {
		return getDealWithString(suggest);
	}
	
	@XmlAnnotation(name="suggest", type=String.class)
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getRecommendIntro() {
		return getDealWithString(recommendIntro);
	}
	
	@XmlAnnotation(name="recommendIntro", type=String.class)
	public void setRecommendIntro(String recommendIntro) {
		this.recommendIntro = recommendIntro;
	}
	public String getSupportDelivery() {
		return supportDelivery;
	}
	
	@XmlAnnotation(name="supportDelivery", type=String.class)
	public void setSupportDelivery(String supportDelivery) {
		this.supportDelivery = supportDelivery;
	}
	public String getSupportPurchase() {
		return supportPurchase;
	}
	
	@XmlAnnotation(name="supportPurchase", type=String.class)
	public void setSupportPurchase(String supportPurchase) {
		this.supportPurchase = supportPurchase;
	}
	public String getFirstLevelDisplayName() {
		return firstLevelDisplayName;
	}
	
	@XmlAnnotation(name="firstLevelDisplayName", type=String.class)
	public void setFirstLevelDisplayName(String firstLevelDisplayName) {
		this.firstLevelDisplayName = firstLevelDisplayName;
	}
	public List<ContentAttr> getContentAttrs() {
		return contentAttrs;
	}
	
	@XmlAnnotation(name="contentAttrs", type=ArrayList.class, target=ContentAttr.class)
	public void setContentAttrs(List<ContentAttr> contentAttrs) {
		this.contentAttrs = contentAttrs;
	}
	public List<ContentRel> getContentRels() {
		return contentRels;
	}
	
	@XmlAnnotation(name="contentRels", type=ArrayList.class, target=ContentRel.class)
	public void setContentRels(List<ContentRel> contentRels) {
		this.contentRels = contentRels;
	}
	public List<ContentOther> getContentOthers() {
		return contentOthers;
	}
	
	@XmlAnnotation(name="contentOthers", type=ArrayList.class, target=ContentOther.class)
	public void setContentOthers(List<ContentOther> contentOthers) {
		this.contentOthers = contentOthers;
	}
	public List<ContentBackground> getContentBackgrounds() {
		return contentBackgrounds;
	}
	
	@XmlAnnotation(name="contentBackgrounds", type=ArrayList.class, target=ContentBackground.class)
	public void setContentBackgrounds(List<ContentBackground> contentBackgrounds) {
		this.contentBackgrounds = contentBackgrounds;
	}
	public List<ContentPhotos> getContentPhotos() {
		return contentPhotos;
	}
	
	@XmlAnnotation(name="contentPhotos", type=ArrayList.class, target=ContentPhotos.class)
	public void setContentPhotos(List<ContentPhotos> contentPhotos) {
		this.contentPhotos = contentPhotos;
	}
	public List<ContentVideo> getContentVideos() {
		return contentVideos;
	}
	
	@XmlAnnotation(name="contentVideos", type=ArrayList.class, target=ContentVideo.class)
	public void setContentVideos(List<ContentVideo> contentVideos) {
		this.contentVideos = contentVideos;
	}
	public List<ContentRecommendPhotos> getContentRecommendPhotos() {
		return contentRecommendPhotos;
	}
	
	@XmlAnnotation(name="contentRecommendPhotos", type=ArrayList.class, target=ContentRecommendPhotos.class)
	public void setContentRecommendPhotos(
			List<ContentRecommendPhotos> contentRecommendPhotos) {
		this.contentRecommendPhotos = contentRecommendPhotos;
	}
	public List<ContentPartner> getContentPartners() {
		return contentPartners;
	}
	
	@XmlAnnotation(name="contentPartners", type=ArrayList.class, target=ContentPartner.class)
	public void setContentPartners(List<ContentPartner> contentPartners) {
		this.contentPartners = contentPartners;
	}
	public List<ContentSocialMedia> getContentSocialMedias() {
		return contentSocialMedias;
	}
	
	@XmlAnnotation(name="contentSocialMedias", type=ArrayList.class, target=ContentSocialMedia.class)
	public void setContentSocialMedias(List<ContentSocialMedia> contentSocialMedias) {
		this.contentSocialMedias = contentSocialMedias;
	}
	
	public String getTvLogoLocalUrl() {
		return tvLogoLocalUrl;
	}

	@XmlAnnotation(name="tvLogoLocalUrl", type=String.class)
	public void setTvLogoLocalUrl(String tvLogoLocalUrl) {
		this.tvLogoLocalUrl = tvLogoLocalUrl;
	}

	public String getTvThumbnailLocalUrl() {
		return tvThumbnailLocalUrl;
	}

	@XmlAnnotation(name="tvThumbnailLocalUrl", type=String.class)
	public void setTvThumbnailLocalUrl(String tvThumbnailLocalUrl) {
		this.tvThumbnailLocalUrl = tvThumbnailLocalUrl;
	}

	public String getIconLocalUrl() {
		return iconLocalUrl;
	}

	@XmlAnnotation(name="iconLocalUrl", type=String.class)
	public void setIconLocalUrl(String iconLocalUrl) {
		this.iconLocalUrl = iconLocalUrl;
	}
	
	

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	@XmlAnnotation(name="currencySymbol", type=String.class)
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	
	public List<ContentCategory> getCategorys() {
		return categorys;
	}

	@XmlAnnotation(name="categorys", type=ArrayList.class, target=ContentCategory.class)
	public void setCategorys(List<ContentCategory> categorys) {
		this.categorys = categorys;
	}
	
	
	public String getQrLogoUrl() {
		return qrLogoUrl;
	}

	@XmlAnnotation(name="qrLogoUrl", type=String.class)
	public void setQrLogoUrl(String qrLogoUrl) {
		this.qrLogoUrl = qrLogoUrl;
	}

	public String getQrLogoLocalUrl() {
		return qrLogoLocalUrl;
	}

	@XmlAnnotation(name="qrLogoLocalUrl", type=String.class)
	public void setQrLogoLocalUrl(String qrLogoLocalUrl) {
		this.qrLogoLocalUrl = qrLogoLocalUrl;
	}

	public String getQrEnLogoUrl() {
		return qrEnLogoUrl;
	}

	@XmlAnnotation(name="qrEnLogoUrl", type=String.class)
	public void setQrEnLogoUrl(String qrEnLogoUrl) {
		this.qrEnLogoUrl = qrEnLogoUrl;
	}

	public String getQrEnLogoLocalUrl() {
		return qrEnLogoLocalUrl;
	}

	@XmlAnnotation(name="qrEnLogoLocalUrl", type=String.class)
	public void setQrEnLogoLocalUrl(String qrEnLogoLocalUrl) {
		this.qrEnLogoLocalUrl = qrEnLogoLocalUrl;
	}

	public String getQrTwLogoUrl() {
		return qrTwLogoUrl;
	}

	@XmlAnnotation(name="qrTwLogoUrl", type=String.class)
	public void setQrTwLogoUrl(String qrTwLogoUrl) {
		this.qrTwLogoUrl = qrTwLogoUrl;
	}

	public String getQrTwLogoLocalUrl() {
		return qrTwLogoLocalUrl;
	}

	@XmlAnnotation(name="qrTwLogoLocalUrl", type=String.class)
	public void setQrTwLogoLocalUrl(String qrTwLogoLocalUrl) {
		this.qrTwLogoLocalUrl = qrTwLogoLocalUrl;
	}

	public String getQrX() {
		return qrX;
	}

	@XmlAnnotation(name="qrX", type=String.class)
	public void setQrX(String qrX) {
		this.qrX = qrX;
	}

	public String getQrY() {
		return qrY;
	}

	@XmlAnnotation(name="qrY", type=String.class)
	public void setQrY(String qrY) {
		this.qrY = qrY;
	}

	public String getQrWidth() {
		return qrWidth;
	}

	@XmlAnnotation(name="qrWidth", type=String.class)
	public void setQrWidth(String qrWidth) {
		this.qrWidth = qrWidth;
	}

	public String getQrHeight() {
		return qrHeight;
	}

	@XmlAnnotation(name="qrHeight", type=String.class)
	public void setQrHeight(String qrHeight) {
		this.qrHeight = qrHeight;
	}

	public String getQrCode() {
		return qrCode;
	}

	@XmlAnnotation(name="qrCode", type=String.class)
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	public String getQrEnCode() {
		return qrEnCode;

	}

	@XmlAnnotation(name="qrEnCode", type=String.class)
	public void setQrEnCode(String qrEnCode) {
		this.qrEnCode = qrEnCode;
	}

	public String getQrTwCode() {
		return qrTwCode;
	}

	@XmlAnnotation(name="qrTwCode", type=String.class)
	public void setQrTwCode(String qrTwCode) {
		this.qrTwCode = qrTwCode;
	}

	public String getPrice2() {
		return getDealWithString(price2);
	}

	@XmlAnnotation(name="price2", type=String.class)
	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getHeatIndex() {
		return heatIndex;
	}

	@XmlAnnotation(name="heatIndex", type=String.class)
	public void setHeatIndex(String heatIndex) {
		this.heatIndex = heatIndex;
	}


	private boolean effective = true;
	public boolean isEffective(){
		return effective;
	}
	
	public void setEffective(boolean flag){
		this.effective = flag;
	}
	
	private String getDealWithString(String result){
		
		if(TextUtils.isEmpty(result)){
			return "";
		}
		
		String temp = result.replace("<![CDATA[", "");
		try{
			temp = temp.substring(0, temp.length()-3);
		}catch(Exception e){
			temp = result;
		}
		
		return temp;
	}
}