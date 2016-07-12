/*package com.hitv.android.uiversion2.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.custom.FlowLayout;

public class TestActivity extends Activity{
	
	private ViewGroup xiaoyu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		xiaoyu = new FlowLayout(this);
		xiaoyu.setBackgroundResource(R.drawable.service_bg);
		View a = new View(this);
		a.setFocusable(true);
		a.setBackgroundResource(R.drawable.service_bg);
		View b = new View(this);
		b.setFocusable(true);
		b.setBackgroundResource(R.drawable.service_bg);
		View c = new View(this);
		c.setFocusable(true);
		c.setBackgroundResource(R.drawable.service_bg);
		xiaoyu.setPadding(30, 30, 30, 30);
		xiaoyu.addView(a, new ViewGroup.MarginLayoutParams(200, 200));
		xiaoyu.addView(b, new ViewGroup.MarginLayoutParams(200, 200));
		xiaoyu.addView(c, new ViewGroup.MarginLayoutParams(200, 200));
		((ViewGroup)findViewById(R.id.main)).addView(xiaoyu);
	}

}
*/