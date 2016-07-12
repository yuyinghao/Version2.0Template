package com.hitv.android.uiversion2.builder;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.bean.ButtonBean;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentPartner;
import com.hitv.android.uiversion2.custom.MeasureLayout;
import com.hitv.android.uiversion2.custom.SlideScrollView;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickListener;

public abstract class ScrollBuilder {
	
	protected Context context;
	protected SlideScrollView slideScrollView;
	protected MeasureLayout contentLayout;
	
	public ScrollBuilder(Context context){
		this.context = context;
		slideScrollView = new SlideScrollView(context);
		slideScrollView.setFocusable(true);
		slideScrollView.setVerticalScrollBarEnabled(false);
		contentLayout = new MeasureLayout(context);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		slideScrollView.addView(contentLayout, layoutParams);
	}
	
	public abstract void setService(Content content, OnServiceClickListener listener, String language);
	
	public abstract void setAbout(String content);
	
	public abstract void setInformation(String promotion, String openTime, String telephone, String address);
	
	public abstract void setTips(String tips);
	
	public abstract void setStringInform(String title, String content);
	
	public abstract void setPartners(List<ContentPartner> partners);
	
	public abstract void setDestinations(GroupAdapter adapter, OnItemClickListener listener);
	
	public abstract void setPictures(GroupAdapter adapter, OnItemClickListener listener);
	
	public abstract void setHorizontalList(String title, GroupAdapter adapter, OnItemClickListener listener);
	
	public abstract void setButtons(ButtonBean... beans);
	
	public void setContentPadding(int left, int top, int right, int bottom){
		contentLayout.setPadding(left, top, right, bottom);
	}
	
	public void scrollToTop(){
		slideScrollView.smoothScrollTo(0, 0);
	}
	
	public SlideScrollView createView(){
		return slideScrollView;
	}
	
	public void clearView(){
		contentLayout.removeAllViews();
	}
	
	protected void addView(View view, LinearLayout.LayoutParams layoutParams){
		contentLayout.addView(view, layoutParams);
	}
	
	public void recycleView(){
		clearView();
		slideScrollView.removeAllViews();
		contentLayout = null;
		slideScrollView = null;
		context = null;
	}

}
