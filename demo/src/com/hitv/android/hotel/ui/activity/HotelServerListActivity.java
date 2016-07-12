package com.hitv.android.hotel.ui.activity;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.bean.Channel;
import com.hitv.android.hotel.mvp.presenters.HotelServerListPresenter;
import com.hitv.android.hotel.mvp.views.IHotelServerList;
import com.hitv.android.hotel.ui.adapter.ServerItemAdapter;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.custom.CustomHomeItemLayout;
import com.hitv.android.uiversion2.dialog.CustomProgressBarDialog;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.utils.ToastCommom;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(HotelServerListPresenter.class)
public class HotelServerListActivity extends BaseActivity<HotelServerListPresenter> implements IHotelServerList{
	
	private View mainView;
	private CustomHomeItemLayout layout;
	
	private ServerItemAdapter adapter;
	
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
		adapter = new ServerItemAdapter(this);
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
	public void updateData(List<Channel> channels) {
		adapter.addContents(channels);
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
	public void startOtherApp(String packageName, String activityName,
			Bundle bundle) {
		
		try{
			ComponentName com = new ComponentName(packageName, activityName);
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setComponent(com);

			intent.putExtras(bundle);

			startActivity(intent);
		}catch(Exception e){
		}
	}

	@Override
	public void openDetailActivity(Bundle bundle) {
		Intent intent = new Intent(this, HotelServerDetailActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openAboutActivity(Bundle bundle) {
		Intent intent = new Intent(this, HotelAboutActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openMemberActivity(Bundle bundle) {
		Intent intent = new Intent(this, HotelMemberActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openDiningRoomActivity(Bundle bundle) {
		Intent intent = new Intent(this, DiningRoomActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openCategoryActivity(Bundle bundle) {
		Intent intent = new Intent(this, HotelCategoryListActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		MobclickAgent.onPageStart("HotelServerListActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0001", "0", "02", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		MobclickAgent.onPageEnd("HotelServerListActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0001", "close");
	}
	
	private long exitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2000)
			{
				ToastCommom.createToastConfig().ToastShow(this, null, Toast.LENGTH_SHORT);
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				
				System.exit(0);
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
