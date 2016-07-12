package com.hitv.android.hotel.mvp.model;

import com.hitv.android.hotel.bean.ChildChannelResponse;

public interface IHotelServerListModel {
	
	public String getHotelName(String langValue, String language);
	
	public void getChildChannelResponse(String baseUrl, String locationId, String channelId, String language, OnGetChildChannelListener listener);
	
	public void cancelResponse(String baseUrl, String locationId, String channelId, String language);
	
	public String getContentUrl(String baseUrl, String locationId, String channelId, String language);

	public String getCategoryUrl(String baseUrl, String locationId, String channelId, String language);

	public String getChannelUrl(String baseUrl, String locationId, String channelId, String language);
	
	public interface OnGetChildChannelListener{
		public void getCache(ChildChannelResponse response);
		public void getNet(ChildChannelResponse response);
	}
}
