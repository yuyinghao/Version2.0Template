package com.hitv.android.hotel.ui.activity;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.bean.Category;
import com.hitv.android.hotel.mvp.presenters.HotelCategoryListPresenter;
import com.hitv.android.hotel.mvp.views.IHotelCategoryList;
import com.hitv.android.hotel.ui.adapter.CategoryItemAdapter;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.custom.CustomHomeItemLayout;
import com.hitv.android.uiversion2.dialog.CustomProgressBarDialog;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.process.FocusProcessor;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(HotelCategoryListPresenter.class)
public class HotelCategoryListActivity extends BaseActivity<HotelCategoryListPresenter> implements IHotelCategoryList{
	
	private View mainView;
	private CustomHomeItemLayout layout;
	
	private CategoryItemAdapter adapter;
	
	private CustomProgressBarDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serverlist_layout);
		initView();
		handler.post(new Runnable() {
			public void run() {
				getPresenter().createData(getIntent().getExtras());
			}
		});
	}
	
	public void initView(){
		mainView = findViewById(R.id.servermainview);
		layout = (CustomHomeItemLayout)findViewById(R.id.server_list);
		adapter = new CategoryItemAdapter(this);
		layout.setContentAdapter(adapter);
		layout.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				getPresenter().openChild(position);
			}
		});
		dialog = new CustomProgressBarDialog(this);
	}

	@Override
	public Context getContext() {
		return this;
	}
	
	@Override
	public void setBackGround(Drawable drawable) {
		mainView.setBackground(drawable);
	}

	@Override
	public void showProgressDialog(boolean flag) {
		if(flag){
			dialog.show();
		}else{
			dialog.dismiss();
		}
	}

	@Override
	public void updateData(List<Category> categorys) {
		adapter.addContents(categorys);
	}

	@Override
	public void openCategoryActivity(Bundle bundle) {
		Intent intent = new Intent(this, HotelCategoryListActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openServerDetailActivit(Bundle bundle) {
		Intent intent = new Intent(this, HotelServerDetailActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		FocusProcessor.focusCache.remove(FocusProcessor.OTHER_FOCUSE_VIEW);
		
		MobclickAgent.onPageEnd("HotelCategoryListActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		MobclickAgent.onPageStart("HotelCategoryListActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}
}