package com.hitv.android.hotel.mvp.presenters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nucleus.presenter.Presenter;
import android.os.Bundle;

import com.hitv.android.hotel.bean.Category;
import com.hitv.android.hotel.bean.CategoryResponse;
import com.hitv.android.hotel.mvp.model.CategoryModel;
import com.hitv.android.hotel.mvp.model.ICategoryModel;
import com.hitv.android.hotel.mvp.model.ICategoryModel.OnGetCategoryResponselListener;
import com.hitv.android.hotel.mvp.views.IDiningRoom;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.statistics.sdk.SDKManager;
import com.umeng.analytics.MobclickAgent;

public class DiningRoomPresenter extends Presenter<IDiningRoom> {

	private ICategoryModel model;
	private List<Category> categorys = new ArrayList<Category>();
	
	private int currentPosition = 0;

	public DiningRoomPresenter() {
		model = new CategoryModel();
	}

	public void createData(Bundle bundle) {
		if (bundle == null)
			return;
		getView().showDialog(true);
		String path = bundle.getString("path");
		model.getCategoryResponse(path, new OnGetCategoryResponselListener() {

			@Override
			public void getNet(CategoryResponse response) {
				dealWithData(response);
			}

			@Override
			public void getCache(CategoryResponse response) {
				dealWithData(response);
			}
		});
	}

	private void dealWithData(CategoryResponse response) {

		if (getView() == null)
			return;

		getView().showDialog(false);
		if (response != null) {

			List<Category> temps = response.getCategorys();
			if (temps != null) {
				
				categorys.clear();
				for(Category category : temps){
					if(category != null && category.getHasRelContent() > 0){
						categorys.add(category);
					}
				}
				
				if(categorys.size() > 0){
					getView().updateTopButton(categorys);
					Category category = categorys.get(currentPosition);
					String path = model
							.getContentResponseUrl(GlobalParams.BASEURL,
									GlobalParams.LOCATION_ID,
									category.getContentCategoryId()+"",
									GlobalParams.IP_LANAGE);
					uploadData(path, category.getCategoryName());
					getView().onDataComplete(path);
				}
			}
		} else {
			// TODO 提示获取数据失败
		}
	}
	
	public void updateData(int position){
		if(categorys.size() > position && currentPosition != position){
			currentPosition = position;
			Category category = categorys.get(currentPosition);
			String path = model
					.getContentResponseUrl(GlobalParams.BASEURL,
							GlobalParams.LOCATION_ID,
							category.getContentCategoryId()+"",
							GlobalParams.IP_LANAGE);
			uploadData(path, category.getCategoryName());
			getView().setContentData(path);
		}
	}
	
	private void uploadData(String path, String name){

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("path", path);
		map.put("name", name);
		MobclickAgent.onEvent(getView().getContext(), "inroomdining", map);
		
		SDKManager.onEvent("inroomdining", map);
	}

}
