package com.hitv.android.hotel.ui.activity;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import android.os.Bundle;

import com.hitv.android.hotel.mvp.presenters.HotelAboutPresenter;
import com.hitv.android.hotel.mvp.views.IHotelAbout;
import com.hitv.android.hotel.ui.adapter.BannerAdapter;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.exceptions.PagerInitException;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageClickListener;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(HotelAboutPresenter.class)
public class HotelAboutActivity extends TemplateActivity<HotelAboutPresenter> implements IHotelAbout{
	
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
		director.construct(contents, new BannerAdapter(this), index);
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
		
		MobclickAgent.onPageStart("HotelAboutActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		MobclickAgent.onPageEnd("HotelAboutActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}

}
