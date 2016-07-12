package com.hitv.android.hotel.mvp.model;

import android.text.TextUtils;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.Request.Method;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.utils.NetworkUtils;
import com.hitv.android.uiversion2.bean.ContentResponse;
import com.hitv.android.uiversion2.dao.ContentDao;

public class ContentModel implements IContentModel{

	@Override
	public void getContentResponse(String url, final OnGetContentResponselListener listener) {
		NetworkUtils.getInstance().getConfig(url, Method.GET, url, GlobalParams.expireTime, new Listener<String>() {
			private String temp;
			@Override
			public void onSuccess(String arg0) {
				if(TextUtils.isEmpty(temp) || !temp.equals(arg0)){
					ContentResponse contentResponse = new ContentDao().getContent(arg0);
					if(listener != null){
						listener.getNet(contentResponse);
					}
				}
			}
			
			@Override
			public void onCacheSuccess(String response) {
				super.onCacheSuccess(response);
				temp = response;
				ContentResponse contentResponse = new ContentDao().getContent(response);
				if(listener != null){
					listener.getCache(contentResponse);
				}
			}
		});
	}

	@Override
	public void cancelResponse(String url) {
		NetworkUtils.getInstance().onActivityStop(url);
	}

	@Override
	public String getChannelContentUrl(String baseUrl, String locationId,
			String channelId, String language) {

		StringBuilder sb = new StringBuilder();

		sb.append(baseUrl).append("cms/ext/getBasicContent?filterLocationId=")
				.append(locationId).append("&contentLanguage=")
				.append(language).append("&childChannelId=").append(channelId)
				.append("&orderBy=1&orderDirection=2");

		return sb.toString();
	}

}
