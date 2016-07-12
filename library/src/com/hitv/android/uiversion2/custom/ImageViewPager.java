package com.hitv.android.uiversion2.custom;

import java.lang.reflect.Field;

import com.hitv.android.uiversion2.process.FixedSpeedScroller;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ImageViewPager extends ViewPager {
	private Context context;
	public ImageViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		changeViewPageScroller(200);
		initAnimation();
	}
	
	private void initAnimation() {
		this.setPageTransformer(true, new PageTransformer() {
			@Override
			public void transformPage(View view, float position) {
				int pageWidth = view.getWidth();
				if (position < -1) {
					view.setAlpha(0);
					view.setAlpha(0);
				} else if (position <= 0) {
					view.setAlpha(1 + position);
					view.setTranslationX((pageWidth * -position));
				} else if (position <= 1) {
					view.setAlpha(1 - position);
					view.setTranslationX((pageWidth * -position));
				} else {
					view.setAlpha(0);
				}
			}
		});
	}
	
	/**
	 * 反射改变viewpager滑动速度
	 */
	private void changeViewPageScroller(int duration) {
		try {
			Field mField = ViewPager.class.getDeclaredField("mScroller");
			mField.setAccessible(true);
			FixedSpeedScroller scroller;
			scroller = new FixedSpeedScroller(context, new LinearInterpolator());
			scroller.setmDuration(duration);
			mField.set(this, scroller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDuration(int duration){
		changeViewPageScroller(duration);
	}
}
