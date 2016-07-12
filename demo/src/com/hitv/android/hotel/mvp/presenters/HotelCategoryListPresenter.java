package com.hitv.android.hotel.mvp.presenters;

import java.util.HashMap;
import java.util.List;

import nucleus.presenter.Presenter;
import android.os.Bundle;

import com.hitv.android.hotel.bean.Category;
import com.hitv.android.hotel.bean.CategoryResponse;
import com.hitv.android.hotel.mvp.model.CategoryModel;
import com.hitv.android.hotel.mvp.model.ICategoryModel;
import com.hitv.android.hotel.mvp.model.ICategoryModel.OnGetCategoryResponselListener;
import com.hitv.android.hotel.mvp.views.IHotelCategoryList;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.statistics.sdk.SDKManager;
import com.umeng.analytics.MobclickAgent;

public class HotelCategoryListPresenter extends Presenter<IHotelCategoryList> {

	private ICategoryModel model;

	private List<Category> categorys;

	public HotelCategoryListPresenter() {
		model = new CategoryModel();
	}

	public void createData(Bundle bundle) {
		if (bundle == null)
			return;
		getView().showProgressDialog(true);
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
		getView().showProgressDialog(false);
		if (response != null) {
			categorys = response.getCategorys();
			if (categorys != null) {
				getView().updateData(categorys);
			}
		} else {
			// TODO 提示获取信息失败
		}
	}

	public void openChild(int position) {

		Category category = categorys.get(position);
		openChildView(category);
	}

	private void openChildView(Category category) {

		int openType = 2;
		try {
			openType = Integer.parseInt(category.getOpenType());
		} catch (Exception e) {
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("categoryId", category.getContentCategoryId()+"");
		map.put("categoryName", category.getCategoryName());
		map.put("openType", category.getOpenType());
		
		MobclickAgent.onEvent(getView().getContext(), "openCategory", map);
		
		SDKManager.onEvent("openCategory", map);

		Bundle bundle = new Bundle();
		if (openType == 1) {
			bundle.putString("path", model.getCategoryResponseUrl(
					GlobalParams.BASEURL, GlobalParams.LOCATION_ID,
					category.getContentCategoryId() + "",
					GlobalParams.IP_LANAGE));
			getView().openCategoryActivity(bundle);
		} else if (openType == 2) {
			bundle.putString("path", model.getContentResponseUrl(
					GlobalParams.BASEURL, GlobalParams.LOCATION_ID,
					category.getContentCategoryId() + "",
					GlobalParams.IP_LANAGE));
			getView().openServerDetailActivit(bundle);
		}
	}

}
