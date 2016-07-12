package com.hitv.android.hotel.mvp.model;

import org.json.JSONArray;
import org.json.JSONException;

import android.text.TextUtils;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.Request.Method;
import com.hitv.android.hotel.bean.ChildChannelResponse;
import com.hitv.android.hotel.bean.Lang;
import com.hitv.android.hotel.dao.ChannelDao;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.utils.NetworkUtils;

public class HotelServerListModel implements IHotelServerListModel {

	@Override
	public String getHotelName(String langValue, String language) {

		String titleName_EN = "";
		String titleName_ZH = "";
		String titleName_TW = "";
		String finalName = "";

		try {
			if (!TextUtils.isEmpty(langValue)) {
				JSONArray array = new JSONArray(langValue);
				for (int i = 0; i < array.length(); i++) {
					Lang lang = new Lang(array.getJSONObject(i));

					if ("en".equals(lang.getI18n())) {
						titleName_EN = lang.getTitle();
					} else if ("cn".equals(lang.getI18n())) {
						titleName_ZH = lang.getTitle();
					} else if ("tw".equals(lang.getI18n())) {
						titleName_TW = lang.getTitle();
					}
				}
			}

		} catch (JSONException e) {
		}

		if ("en_US".equals(language)) {
			finalName = titleName_EN;
		} else if ("zh_TW".equals(language)) {
			finalName = titleName_TW;
		} else if ("zh_CN".equals(language)) {
			finalName = titleName_ZH;
		}

		return finalName;
	}

	@Override
	public String getContentUrl(String baseUrl, String locationId,
			String channelId, String language) {

		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append("cms/ext/getContent?filterLocationId=")
				.append(locationId).append("&contentLanguage=")
				.append(language).append("&childChannelId=").append(channelId);

		return sb.toString();
	}

	@Override
	public String getCategoryUrl(String baseUrl, String locationId,
			String channelId, String language) {

		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append("cms/ext/getCategory?filterLocationId=")
				.append(locationId).append("&categoryLanguage=")
				.append(language).append("&childChannelId=").append(channelId);

		return sb.toString();
	}

	@Override
	public String getChannelUrl(String baseUrl, String locationId,
			String channelId, String language) {

		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append("cms/ext/getChildChannel")
				.append("?locationId=").append(locationId)
				.append("&channelId=").append(channelId)
				.append("&filterLocationId=").append(locationId)
				.append("&channelLanguage=").append(language)
				.append("&pageNo=0&needContentSize=0");

		return sb.toString();
	}

	@Override
	public void getChildChannelResponse(String baseUrl, String locationId,
			String channelId, String language,
			final OnGetChildChannelListener listener) {
		String url = getChannelUrl(baseUrl, locationId, channelId, language);
		NetworkUtils.getInstance().getConfig(url, Method.GET, url,
				GlobalParams.expireTime, new Listener<String>() {
					private String temp;

					@Override
					public void onSuccess(String arg0) {
						if (TextUtils.isEmpty(temp) || !temp.equals(arg0)) {
							ChildChannelResponse bean = new ChannelDao()
									.getChannelList(arg0);
							if (listener != null) {
								listener.getNet(bean);
							}
						}
					}

					@Override
					public void onCacheSuccess(String response) {
						super.onCacheSuccess(response);
						temp = response;
						ChildChannelResponse bean = new ChannelDao()
								.getChannelList(response);
						if (listener != null) {
							listener.getCache(bean);
						}
					}
				});
	}

	@Override
	public void cancelResponse(String baseUrl, String locationId,
			String channelId, String language) {
		NetworkUtils.getInstance().onActivityStop(
				getChannelUrl(baseUrl, locationId, channelId, language));
	}

}
