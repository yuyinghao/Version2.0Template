package com.hitv.android.uiversion2.builder;

import java.util.List;

import com.hitv.android.uiversion2.adapter.AbstractAdapter;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.bean.ButtonBean;
import com.hitv.android.uiversion2.bean.ContentPartner;
import com.hitv.android.uiversion2.custom.SlideLayout;
import com.hitv.android.uiversion2.exceptions.PagerInitException;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageClickListener;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickListener;
import com.hitv.android.uiversion2.interfaces.OnViewMoreListener;

public interface IDirector<T> {
	public void setScreenHeight(int screenHeight);

	public void setScrollBuilder(ScrollBuilder scrollBuilder);

	public void setTitleBuilder(TitleBuilder titleBuilder);
	
	public void setOnViewMoreListener(OnViewMoreListener listener);
	
	public void setTitleClickListener(OnCurrentPageClickListener listener) throws PagerInitException;

	public SlideLayout getContentView();

	public void construct(final List<T> contents, AbstractAdapter<T> adapter, int index);
	
	public int getPageCurrentItem();
	
	void setService(T content, OnServiceClickListener listener);
	
	void setAbout(String content);
	
	void setInformation(String promotion, String openTime, String telephone, String address);
	
	void setTips(String tips);
	
	void setStringInform(String title, String content);
	
	void setPartners(List<ContentPartner> partners);
	
	void setDestinations(GroupAdapter<T> adapter, OnItemClickListener listener);
	
	void setPictures(GroupAdapter<T> adapter, OnItemClickListener listener);
	
	void setHorizontalList(String title, GroupAdapter<T> adapter, OnItemClickListener listener);
	
	void setButtons(ButtonBean... beans);
	
	public void clearView();
	
	public void onActivityDestroy();
	
	void setIContent(T content);
	 
	boolean onPagerChanged(List<T> list, int position);
}
