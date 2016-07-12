package com.hitv.android.hotel.mvp.model;

import com.hitv.android.uiversion2.bean.ContentResponse;

public interface IContentModel {
	public void getContentResponse(String url, OnGetContentResponselListener listener);
	public void cancelResponse(String url);
	public String getChannelContentUrl(String baseUrl, String locationId, String channelId, String language);
	
	public interface OnGetContentResponselListener{
		public void getCache(ContentResponse response);
		public void getNet(ContentResponse response);
	}
}
