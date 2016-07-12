package com.hitv.android.hotel.ui.activity;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.builder.QrcodeFactory;
import com.umeng.analytics.MobclickAgent;

public class QrCodeActivity extends Activity{
	
	public static final String QRCONTENT = "content";
	public static final String QRLOGO = "logo";
	public static final String DETAIL = "detail";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String content = getIntent().getStringExtra(QRCONTENT);
		String logo = getIntent().getStringExtra(QRLOGO);
		String detail = getIntent().getStringExtra(DETAIL);
		View view = null;
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("qrcode", content);
		MobclickAgent.onEvent(this, "openqrcode", map);
		
		SDKManager.onEvent("openqrcode", map);
		
		if(TextUtils.isEmpty(logo)){
			view = QrcodeFactory.getQrCode(this, content);
		}else{
			view = QrcodeFactory.getQrCode(this, content, logo, detail);
		}
		
		setContentView(view);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("QrCodeActivity");
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		MobclickAgent.onPageStart("QrCodeActivity");
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}

}
