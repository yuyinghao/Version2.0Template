package com.hitv.android.uiversion2.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hitv.android.uiversion2.R;

public abstract class TitleBuilder {
	
	protected Context context;
	
	protected RelativeLayout titleLayout;
	
	public TitleBuilder(Context context){
		this.context = context;
		titleLayout = (RelativeLayout)LayoutInflater.from(context).inflate(R.layout.title_custom, null);
	}
	
	public abstract void setTitleIcon(View view);
	
	public abstract void setTitleText(String text);
	
	public abstract void setRating(float value);
	
	public abstract void setSpecail(String specail);
	
	public View createView(){
		return titleLayout;
	}
	
	public void clearView(){
		((ViewGroup)titleLayout.findViewById(R.id.titleicon)).removeAllViews();
	}
	
	public void recycleView(){
		titleLayout.removeAllViews();
		titleLayout = null;
		context = null;
	}
}
