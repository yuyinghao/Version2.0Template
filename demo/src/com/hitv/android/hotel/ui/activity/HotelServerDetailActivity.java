package com.hitv.android.hotel.ui.activity;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import android.os.Bundle;

import com.hitv.android.hotel.mvp.presenters.HotelServerDetailPresenter;
import com.hitv.android.hotel.mvp.views.IHotelServerDetail;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.adapter.BannerAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.exceptions.PagerInitException;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageClickListener;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(HotelServerDetailPresenter.class)
public class HotelServerDetailActivity extends TemplateActivity<HotelServerDetailPresenter> implements IHotelServerDetail{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler.post(new Runnable() {
			public void run() {
				getPresenter().createData(getIntent().getExtras());
			}
		});
	}

	@Override
	public void constructView(List<Content> contents, int index) {
		director.construct(contents, new BannerAdapter(this, GlobalParams.IP_LANAGE), index);
		try {
			director.setTitleClickListener(new OnCurrentPageClickListener() {
				
				@Override
				public void currentPageClick(int position) {
					getPresenter().initJumpData(position, 0);
				}
			});
		} catch (PagerInitException e) {
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		MobclickAgent.onPageStart("HotelServerDetailActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		MobclickAgent.onPageEnd("HotelServerDetailActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}
}
