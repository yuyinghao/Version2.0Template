package com.hitv.android.hotel.mvp.model;

import com.hitv.android.hotel.bean.CategoryResponse;

public interface ICategoryModel {
	public void getCategoryResponse(String url,
			OnGetCategoryResponselListener listener);

	public void cancelResponse(String url);

	public String getContentResponseUrl(String baseUrl, String locationId,
			String categoryId, String language);
	
	public String getCategoryResponseUrl(String baseUrl, String locationId,
			String categoryId, String language);
	
	public interface OnGetCategoryResponselListener{
		public void getCache(CategoryResponse response);
		public void getNet(CategoryResponse response);
	}
}
