package com.hitv.android.hotel.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.builder.PictureBrowseBuilder;
import com.umeng.analytics.MobclickAgent;

public class ImageActivity extends FragmentActivity{
	
	public static final String POSITION = "POSITION";
	public static final String CONTENT = "CONTENT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PictureBrowseBuilder builder = new PictureBrowseBuilder(this);
		
		setContentView(builder.getView());

		Content content = (Content)getIntent().getSerializableExtra(CONTENT);
		int position = getIntent().getIntExtra(POSITION, 0);
		
		builder.setData(content, position);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		MobclickAgent.onPageStart("ImageActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		super.overridePendingTransition(-1, R.anim.scale_out);
		MobclickAgent.onPageEnd("ImageActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}

}
