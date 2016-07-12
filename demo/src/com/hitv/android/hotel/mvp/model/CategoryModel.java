package com.hitv.android.hotel.mvp.model;

import android.text.TextUtils;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.Request.Method;
import com.hitv.android.hotel.bean.CategoryResponse;
import com.hitv.android.hotel.dao.CategoryDao;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.utils.NetworkUtils;

public class CategoryModel implements ICategoryModel{

	@Override
	public void getCategoryResponse(String url,
			final OnGetCategoryResponselListener listener) {
		NetworkUtils.getInstance().getConfig(url, Method.GET, url,
				GlobalParams.expireTime, new Listener<String>() {
					private String temp;

					@Override
					public void onSuccess(String arg0) {
						if (TextUtils.isEmpty(temp) || !temp.equals(arg0)) {
							CategoryResponse categoryResponse = new CategoryDao()
									.getCategoryList(arg0);
							if (listener != null) {
								listener.getNet(categoryResponse);
							}
						}
					}

					@Override
					public void onCacheSuccess(String response) {
						super.onCacheSuccess(response);
						temp = response;
						CategoryResponse categoryResponse = new CategoryDao()
								.getCategoryList(response);
						if (listener != null) {
							listener.getCache(categoryResponse);
						}
					}
				});
	}

	@Override
	public void cancelResponse(String url) {
		NetworkUtils.getInstance().onActivityStop(url);
	}

	@Override
	public String getContentResponseUrl(String baseUrl, String locationId,
			String categoryId, String language) {
		StringBuilder sb = new StringBuilder();

		sb.append(baseUrl).append("cms/ext/getContent?filterLocationId=")
				.append(locationId).append("&contentLanguage=")
				.append(language).append("&categoryId=").append(categoryId);

		return sb.toString();
	}

	@Override
	public String getCategoryResponseUrl(String baseUrl, String locationId,
			String categoryId, String language) {
		StringBuilder sb = new StringBuilder();

		sb.append(baseUrl).append("cms/ext/getCategory?filterLocationId=")
				.append(locationId).append("&contentLanguage=")
				.append(language).append("&categoryId=").append(categoryId);

		return sb.toString();
	}

}
